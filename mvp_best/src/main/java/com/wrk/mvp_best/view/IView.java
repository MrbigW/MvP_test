package com.wrk.mvp_best.view;

/**
 * Created by MrbigW on 2017/1/10.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: View的接口,设计View的接口时候，要考虑这个接口的必要性
 * View的接口只是为了传输数据而不是为了让Presenter来控制
 * -------------------=.=------------------------
 */

public interface IView {

    void setData(String data);

}
