package bupt.sse.xs.memory;

import bupt.sse.xs.classfile.XClass;
import bupt.sse.xs.object.NormalObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xusong on 2017/4/22.
 * Email:xusong@bupt.edu.cn
 */
public class XHeap {

    List<NormalObject> constInts;
    List<NormalObject> constString;

    List<NormalObject> xInstance;//class objects
    List<XClass> xClasses;//classes in method area


    List<NormalObject> objects = new ArrayList<>();//the objects in heap;
}
