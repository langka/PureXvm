package bupt.sse.xs.classfile.fileitem;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 * this class hold every constant pool item
 * with different flags,different fields will be used,so this can cause a waste of memory;
 */
public class PoolInfo {
    public byte flag;
    public int length;//works for contant utf8
    public int index1;
    public int index2;


    public byte[] val;//now works for utf8,someday will be used to replace every field below
    public int intVal;
    public float floatVal;
    public long longVal;
    public double doubleVal;

}
