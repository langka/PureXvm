package bupt.sse.xs.jvm;

import bupt.sse.xs.classfile.XClass;
import bupt.sse.xs.classfile.XClassScanner;

import java.io.File;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class Main {
    public static void main(String[] args) throws Exception{
       XJvm jvm = new XJvm(args);
       jvm.start();
    }
}
