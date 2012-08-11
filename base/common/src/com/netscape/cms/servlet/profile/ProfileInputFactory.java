package com.netscape.cms.servlet.profile;

import java.util.Enumeration;
import java.util.Locale;

import com.netscape.certsrv.base.IArgBlock;
import com.netscape.certsrv.profile.EProfileException;
import com.netscape.certsrv.profile.IProfileInput;
import com.netscape.certsrv.request.IRequest;
import com.netscape.cms.servlet.profile.model.ProfileInput;

public class ProfileInputFactory {

    public static ProfileInput create(IProfileInput input, IRequest request, Locale locale) throws EProfileException  {
        ProfileInput ret = new ProfileInput();
        ret.setInputId(input.getName(locale));
        Enumeration<String> names = input.getValueNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = input.getValue(name, locale, request);
            if (value != null) {
                ret.setInputAttr(name, value);
            }
        }
        return ret;
    }

    public static ProfileInput create(IProfileInput input, IArgBlock params, Locale locale) throws EProfileException {
        ProfileInput ret = new ProfileInput();
        ret.setInputId(input.getName(locale));
        Enumeration<String> names = input.getValueNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = params.getValueAsString(name, null);
            if (value != null) {
                ret.setInputAttr(name, value);
            }
        }
        return ret;
    }
}