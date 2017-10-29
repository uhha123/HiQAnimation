package vn.ray.animation.constant;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.ray.animation.R;
import vn.ray.animation.model.FileModel;

/**
 * Created by Ray on 10/28/17.
 */

public class DataFactory implements Constant {

    public static List<FileModel> getFiles() {
        List<FileModel> list = new ArrayList<>();
        list.add(new FileModel("Documents", R.drawable.ic_insert_drive_file_white_24dp, R.drawable.bg_yellow, FILE_MODEL.DOCUMENTS));
        list.add(new FileModel("Images", R.drawable.ic_crop_original_white_24dp, R.drawable.bg_green, FILE_MODEL.IMAGES));
        list.add(new FileModel("Videos", R.drawable.ic_slow_motion_video_white_24dp, R.drawable.bg_orange, FILE_MODEL.VIDEOS));
        list.add(new FileModel("System", R.drawable.ic_system_update_white_24dp, R.drawable.bg_brown, FILE_MODEL.SYSTEM));
        list.add(new FileModel("Contacts", R.drawable.ic_perm_contact_calendar_white_24dp, R.drawable.bg_brown, FILE_MODEL.CONTACTS));
        return list;
    }
}
