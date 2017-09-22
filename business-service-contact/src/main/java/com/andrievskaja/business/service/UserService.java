
package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;

/**
 *
 * @author Людмила
 */
public interface UserService {
    
    public UserView createUSer(RegisteForm registeForm);
    
}
