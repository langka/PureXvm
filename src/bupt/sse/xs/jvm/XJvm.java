package bupt.sse.xs.jvm;

import bupt.sse.xs.object.NormalObject;

import java.util.List;

/**
 * Created by xusong on 2017/4/21.
 */
public class XJvm {
    List<Thread> userThreads;
    List<Thread> damonThreads;



    public void init(String[] args){

    }
    public XJvm(String[] args){
        init(args);
    }
    public void start(){
        initMemory();
    }

    // TODO: 2017/4/22 init mem and gc
    private void initMemory(){

    }
}
