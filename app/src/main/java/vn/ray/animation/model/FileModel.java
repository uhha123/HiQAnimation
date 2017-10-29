package vn.ray.animation.model;

import vn.ray.animation.constant.Constant;

/**
 * Created by Ray on 10/28/17.
 */

public class FileModel implements Constant{
    private String title;
    private int icon, bgColor;
    private FILE_MODEL mode;

    public FileModel(String title, int icon, int bgColor, FILE_MODEL mode) {
        this.title = title;
        this.icon = icon;
        this.bgColor = bgColor;
        this.mode = mode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public FILE_MODEL getMode() {
        return mode;
    }

    public void setMode(FILE_MODEL mode) {
        this.mode = mode;
    }
}
