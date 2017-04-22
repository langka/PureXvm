package bupt.sse.xs.classfile;

import bupt.sse.xs.classfile.fileitem.FileAttr;
import bupt.sse.xs.classfile.fileitem.FileField;
import bupt.sse.xs.classfile.fileitem.FileMethod;
import bupt.sse.xs.classfile.fileitem.PoolInfo;

import java.util.List;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class XClass {
    public int magic;
    public int minor_version;
    public int major_version;
    public int constantPoolCount;
    public PoolInfo[] poolInfos;
    public int accessFlags;
    public int thisXClass;
    public int superXClass;
    public int interfacesCount;
    public int[] interfaces;
    public int fieldsCount;
    public FileField[] fields;
    public int methodCount;
    public FileMethod[] methods;
    public int attrCount;
    public FileAttr[] attrs;
}
