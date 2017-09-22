package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.RecordForm;
import com.andrievskaja.business.service.model.view.RecordView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface RecordService {

    /*
    Add new record 
     */
    public RecordView add(RecordForm form);


    public List<RecordView> getAll(Long userId);

    /*
    Delete  task in TaskTable
     */
    public void delete(Long id, Long userId);

    /*
    Edit  record
     */
    public RecordView edit(RecordForm form);


}
