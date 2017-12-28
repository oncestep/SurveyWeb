package com.moyo.listener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用ValueChangeListener:在问题个数不定时，bean中无法加入指定个数bean属性optionId,用以对应存储每各题目被选选项Id
 * 多选题
 */
public class MultiplySelectListener implements ValueChangeListener {
    public static List<Long[]> multiplySelectList = new LinkedList<>();

    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        String[] selectStringList = (String[]) valueChangeEvent.getNewValue();
        Long[] selectOptionList = new Long[selectStringList.length];

        /*  获得被选选项Id数组  */
        for (int i = 0; i < selectOptionList.length; i++) {
            selectOptionList[i] = Long.valueOf(selectStringList[i]);
        }

        /*  选项Id数组存入List保存  */
        multiplySelectList.add(selectOptionList);
    }
}
