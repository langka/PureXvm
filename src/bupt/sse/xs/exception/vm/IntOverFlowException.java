package bupt.sse.xs.exception.vm;

import bupt.sse.xs.exception.BaseException;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class IntOverFlowException extends BaseException{
    private String message;

    public IntOverFlowException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
