package bupt.sse.xs.exception;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class ClassFileException extends BaseException {
    protected String msg;

    public ClassFileException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
