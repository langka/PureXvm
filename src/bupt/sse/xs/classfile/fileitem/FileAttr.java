package bupt.sse.xs.classfile.fileitem;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 * holde the value of attrs int class file
 */
public class FileAttr {
    public int nameIndex;
    // TODO: 2017/4/22 if the length overflow!!
    public int length;
    public byte[] data;//
}
