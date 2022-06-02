package com.gpfei.demo.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1设置写入文件地址和名称
        String fileName = "D:\\write.xlsx";
        //调用easyexcel中的方法完成写操作

        // 这里 需要指定写用哪个class去写
//        ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class).build();
//        WriteSheet writeSheet = EasyExcel.writerSheet("学生列表").build();
//        excelWriter.write(getData(), writeSheet);
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();

//        List<DemoData> list = new ArrayList<>();
//        list = getData();
//        list.forEach(System.out::println);
//        //两个参数，第一个文件路径，第二个实体类class
//        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());

        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();

    }

    //返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("Lucy" + i);
            list.add(data);

        }
        return list;
    }
}
