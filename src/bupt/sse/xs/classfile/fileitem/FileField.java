package bupt.sse.xs.classfile.fileitem;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 * hold the value of field
 */
public class FileField {
    public int accessFlags;
    public int nameIndex;
    public int descriptorIndex;
    public int attrCount;
    public FileAttr[] attrs;
}
