package com.moyo.listener;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用ValueChangeListener:在问题个数不定时，bean中无法加入指定个数bean属性optionId,用以对应存储每各题目被选选项Id
 * 单选题
 */
public class SingleSelectListener implements ValueChangeListener {

    public static List<Long> singleSelectList = new LinkedList<>();

    @Override
    public void processValueChange(ValueChangeEvent valueChangeEvent) throws AbortProcessingException {
        /*  得到被选选项Id  */
        long selectOptionId = Long.parseLong((String)valueChangeEvent.getNewValue());

        /*  将选项加入List保存  */
        singleSelectList.add(selectOptionId);
    }
}
