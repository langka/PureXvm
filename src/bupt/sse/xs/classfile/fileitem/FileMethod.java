package bupt.sse.xs.classfile.fileitem;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 * hold the value of method in class file
 */
public class FileMethod {
    public int accessFlags;
    public int nameIndex;
    public int descriptorIndex;
    public int attrCount;
    public FileAttr[] attrs;
}
