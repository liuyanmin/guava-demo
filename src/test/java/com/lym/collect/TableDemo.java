package com.lym.collect;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * 类似于表格，有row、column、value，通过row和column才能确定value值
 * Created by liuyanmin on 2020/6/21.
 */
public class TableDemo {

    @Test
    public void test() {
        Table<String, String, String> employeeTable = HashBasedTable.create();
        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");
        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "102","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");
        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");

        Map<String,String> rowMap =  employeeTable.row("IBM");
        System.out.println(rowMap);//{101=Mahesh, 102=Ramesh, 103=Suresh}

        Map<String,String> columnMap =  employeeTable.column("102");
        System.out.println(columnMap);//{IBM=Ramesh, Microsoft=Mohan}

        Set<String> employees = employeeTable.rowKeySet();
        System.out.println(employees);//[IBM, Microsoft, TCS]
    }
}
