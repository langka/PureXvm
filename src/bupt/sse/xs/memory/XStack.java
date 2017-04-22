package bupt.sse.xs.memory;

import bupt.sse.xs.interpreter.ByteCodeInterpreter;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class XStack {
    XFrame frame;
    Thread owner;
    ByteCodeInterpreter interpreter;
    int pc;
}
