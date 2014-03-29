package org.nailedtothex.jberetweb.service;

import javax.ejb.Stateless;
import javax.persistence.Tuple;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kyle on 2014/03/29.
 */
@Stateless
public class DataTablesService {
    private final Format sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Format sdfTime = new SimpleDateFormat("HH:mm:ss");

    protected List<List<String>> createAaData(List<Tuple> tuples, Format f) {
        List<List<String>> list = new ArrayList<>(tuples.size());
        for (Tuple t : tuples) {
            List<String> subList = new ArrayList<>(t.getElements().size());
            for (int i = 0; i < t.getElements().size(); i++) {
                Object elem = t.get(i);
                subList.add(elem == null ? "null" : elem instanceof Date ? f.format(elem) : String.valueOf(elem));
            }
            list.add(subList);
        }
        return list;
    }

    public List<List<String>> createAaDataWithTime(List<Tuple> tuples) {
        return createAaData(tuples, sdfTime);
    }

    public List<List<String>> createAaDataWithDateTime(List<Tuple> tuples) {
        return createAaData(tuples, sdfDateTime);
    }
}
