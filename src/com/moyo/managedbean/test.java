package com.moyo.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import java.util.*;

@ManagedBean
@RequestScoped
public class test implements ValueChangeListener {
    List<ques> q=new ArrayList<>();
    Map<String,String> map=new LinkedHashMap<>();
    List<String> showlist=new ArrayList<>();

    public List<String> getShowlist() {
        return showlist;
    }

    public void setShowlist(List<String> showlist) {
        this.showlist = showlist;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    String re;

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public test(){
        ques qu1=new ques();
        String[] s1=new String[2];
        qu1.setTitle("t1");
        s1[0]="o1";
        s1[1]="o2";
        qu1.setOptions(s1);
        q.add(qu1);
        ques qu2=new ques();
        String[] s2=new String[2];
        qu2.setTitle("t2");
        s2[0]="o3";
        s2[1]="o4";
        qu2.setOptions(s2);
        q.add(qu2);
        ques qu3=new ques();
        String[] s3=new String[2];
        qu3.setTitle("t3");
        s3[0]="o5";
        s3[1]="o6";
        qu3.setOptions(s3);
        q.add(qu3);
    }

    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        System.out.println(valueChangeEvent);
        String id=valueChangeEvent.getComponent().getId();
        String value=valueChangeEvent.getNewValue().toString();
        map.put(id,value);
    }


    public class ques{
        String title;
        String[] options;

        public String[] getOptions() {
            return options;
        }

        public void setOptions(String[] options) {
            this.options = options;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }


    public List<ques> getQ() {
        return q;
    }

    public void setQ(List<ques> q) {
        this.q = q;
    }

    public void show(){
        for(String s:map.keySet()){
            showlist.add(map.get(s));
        }
    }
}
