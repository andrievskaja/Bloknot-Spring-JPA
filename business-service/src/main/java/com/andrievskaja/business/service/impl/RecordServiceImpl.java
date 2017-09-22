package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.Record;
import com.andrievskaja.business.model.User;
import com.andrievskaja.business.service.RecordService;
import com.andrievskaja.business.service.model.form.RecordForm;
import com.andrievskaja.business.service.model.view.RecordView;
import com.andrievskaja.dao.RecordRepository;
import com.andrievskaja.dao.UserRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author Людмила
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor(null, true));
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(
                        new SimpleDateFormat("dd.MM.yyyy"), false)
        );
    }
    private final ModelMapper mapper = new ModelMapper();

    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;

    /*
    Add new record
     */
    @Transactional
    @Override
    public RecordView add(RecordForm form) {
        User user = userRepository.findOne(form.getUserId());
        if (user == null) {
            return null;
        }
        Record record = new Record();
        mapper.map(form, record);
        record.setDate(new Date());
        record.setUser(user);

        recordRepository.save(record);
        return mapper.map(record, RecordView.class);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RecordView> getAll(Long userId) {
        List<Record> list = recordRepository.findByUserId(userId);
        List<RecordView> listView = new ArrayList(list.size());
        for (Record record : list) {
            listView.add(mapper.map(record, RecordView.class));
        }
        return listView;
    }


    /*
    Delete the record 
     */
    @Transactional
    @Override
    public void delete(Long id, Long userId) {
        Record record = recordRepository.findByIdAndUserId(id, userId);
        if (record == null) {
        }
        recordRepository.delete(record);
    }

    /*
    Edit  record
     */
    @Transactional
    @Override
    public RecordView edit(RecordForm form) {
        Record record = recordRepository.findOne(form.getId());
        if (record == null) {
            return null;
        }
        record.setText(form.getText());
        recordRepository.save(record);
        return mapper.map(record, RecordView.class);
    }

}
