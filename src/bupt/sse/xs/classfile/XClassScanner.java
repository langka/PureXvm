package bupt.sse.xs.classfile;

import bupt.sse.xs.classfile.fileitem.FileAttr;
import bupt.sse.xs.classfile.fileitem.FileField;
import bupt.sse.xs.classfile.fileitem.FileMethod;
import bupt.sse.xs.classfile.fileitem.PoolInfo;
import bupt.sse.xs.exception.ClassFileException;
import bupt.sse.xs.exception.vm.IntOverFlowException;

import java.io.*;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class XClassScanner {
    public static int FILE_NOT_FOUND = 1;


    public class XClassWrapper {
        public XClass xClass;
        public int state;
    }


    private static XClassScanner instance = null;

    public static XClassScanner getInstance() {
        if (instance == null) {
            synchronized (XClassScanner.class) {
                if (instance == null)
                    instance = new XClassScanner();
            }
        }
        return instance;
    }

    private XClassScanner() {

    }

    public XClassWrapper parseClassFile(File file) throws ClassFileException {
        XClassWrapper wrapper = new XClassWrapper();
        DataInputStream fis = null;
        try {
            fis = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            wrapper.state = FILE_NOT_FOUND;
            return wrapper;
        }
        wrapper.xClass = new XClass();
        String errMsg = null;
        try {
            wrapper.xClass.magic = fis.readInt();
            wrapper.xClass.minor_version = fis.readUnsignedShort();
            wrapper.xClass.major_version = fis.readUnsignedShort();
            wrapper.xClass.constantPoolCount = fis.readUnsignedShort();
            wrapper.xClass.poolInfos = new PoolInfo[wrapper.xClass.constantPoolCount];
            for (int i = 1; i < wrapper.xClass.constantPoolCount; i++) {//read the constant pool
                wrapper.xClass.poolInfos[i] = createInfoFromStream(fis);
            }
            wrapper.xClass.accessFlags = fis.readUnsignedShort();
            wrapper.xClass.thisXClass = fis.readUnsignedShort();
            wrapper.xClass.superXClass = fis.readUnsignedShort();
            wrapper.xClass.interfacesCount = fis.readUnsignedShort();
            wrapper.xClass.interfaces = new int[wrapper.xClass.interfacesCount];
            for (int i = 0; i < wrapper.xClass.interfacesCount; i++) {
                wrapper.xClass.interfaces[i] = fis.readUnsignedShort();
            }
            wrapper.xClass.fieldsCount = fis.readUnsignedShort();
            wrapper.xClass.fields = new FileField[wrapper.xClass.fieldsCount];
            for (int i = 0; i < wrapper.xClass.fieldsCount; i++) {
                try {
                    wrapper.xClass.fields[i] = createFieldFromStream(fis);
                } catch (IntOverFlowException e) {
                    throw new ClassFileException("Attr data overflow,not supported more than Integer.MAXVALUE");
                }
            }
            wrapper.xClass.methodCount = fis.readUnsignedShort();
            wrapper.xClass.methods = new FileMethod[wrapper.xClass.methodCount];
            for (int i = 0; i < wrapper.xClass.methodCount; i++) {
                // TODO: 2017/4/22 compelte this!
                try {
                    wrapper.xClass.methods[i] = createMethodFromStream(fis);
                } catch (IntOverFlowException e) {
                    throw new ClassFileException("Attr data overflow,not supported more than Integer.MAXVALUE");
                }
            }
            wrapper.xClass.attrCount = fis.readUnsignedShort();
            wrapper.xClass.attrs = new FileAttr[wrapper.xClass.attrCount];
            for (int i = 0; i < wrapper.xClass.attrCount; i++) {
                try {
                    wrapper.xClass.attrs[i] = createAttrFromStream(fis);
                } catch (IntOverFlowException e) {
                    throw new ClassFileException("Attr data overflow,not supported more than Integer.MAXVALUE");
                }
            }
        } catch (IOException e) {
            errMsg = "failed to read class file！";
            throw new ClassFileException(errMsg);
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wrapper;
    }

    private PoolInfo createInfoFromStream(DataInputStream dis) throws IOException, ClassFileException {
        PoolInfo info = new PoolInfo();
        info.flag = dis.readByte();
        switch (info.flag) {
            case 1:
                info.length = dis.readUnsignedShort();
                info.val = new byte[info.length];
                readActualBytes(info.val, dis, info.length);
                break;
            case 3:
                info.intVal = dis.readInt();
                break;
            case 4:
                info.floatVal = dis.readFloat();
                break;
            case 5:
                info.longVal = dis.readLong();
                break;
            case 6:
                info.doubleVal = dis.readDouble();
                break;
            case 7:
            case 8:
            case 16:
                info.index1 = dis.readUnsignedShort();
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 18:
                info.index1 = dis.readUnsignedShort();
                info.index2 = dis.readUnsignedShort();
                break;
            case 15:
                info.intVal = dis.readByte();
                info.index1 = dis.readUnsignedShort();
                break;
            default:
                throw new ClassFileException("invalid contant pool item tag!");
        }
        return info;
    }

    private FileField createFieldFromStream(DataInputStream dis) throws IOException, ClassFileException, IntOverFlowException {
        FileField field = new FileField();
        field.accessFlags = dis.readUnsignedShort();
        field.nameIndex = dis.readUnsignedShort();
        field.descriptorIndex = dis.readUnsignedShort();
        field.attrCount = dis.readUnsignedShort();
        field.attrs = new FileAttr[field.attrCount];
        for (int i = 0; i < field.attrCount; i++) {
            field.attrs[i] = createAttrFromStream(dis);
        }
        return field;
    }

    private FileAttr createAttrFromStream(DataInputStream dis) throws IOException, ClassFileException, IntOverFlowException {
        FileAttr attr = new FileAttr();
        attr.nameIndex = dis.readUnsignedShort();
        attr.length = dis.readInt();
        if (attr.length < 0)
            throw new IntOverFlowException("length of attr overflow,not supported!");
        attr.data = new byte[attr.length];
        readActualBytes(attr.data, dis, attr.length);
        return attr;
    }

    private FileMethod createMethodFromStream(DataInputStream dis) throws IOException, ClassFileException, IntOverFlowException {
        FileMethod method = new FileMethod();
        method.accessFlags = dis.readUnsignedShort();
        method.nameIndex = dis.readUnsignedShort();
        method.descriptorIndex = dis.readUnsignedShort();
        method.attrCount = dis.readUnsignedShort();
        method.attrs = new FileAttr[method.attrCount];
        for(int i=0;i<method.attrCount;i++){
            method.attrs[i] = createAttrFromStream(dis);
        }
        return method;
    }

    //真正的读满一个缓冲区
    private void readActualBytes(byte[] buffer, DataInputStream dis, int length) throws IOException {
        int actual = dis.read(buffer);
        if (actual < length) {
            for (int i = actual; i < length; i++) {
                buffer[actual] = dis.readByte();
            }
        }
    }


    //bytes to long
    public static long bytes2long(byte[] b) {
        long temp = 0;
        long res = 0;
        for (int i = 0; i < 8; i++) {
            res <<= 8;
            temp = b[i] & 0xff;
            res |= temp;
        }
        return res;
    }

}
