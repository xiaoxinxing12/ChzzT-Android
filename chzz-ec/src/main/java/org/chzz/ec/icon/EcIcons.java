package org.chzz.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by copy on 2017/8/6.
 * Description:
 * User: copy
 * Date: 2017-08-06
 * Time: 上午11:41
 */
public enum EcIcons implements Icon {
    icon_t('\ue75d');
    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
