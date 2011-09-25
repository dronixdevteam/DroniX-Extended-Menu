package org.dronix.android.dronixextendedmenu;

import com.stericson.RootTools.RootTools;
import com.stericson.RootTools.RootToolsException;

import java.io.IOException;
import java.util.List;

/*
 * Author: Ivan Morgillo
 * E-mail: imorgillo [at] gmail [dot] com
 * Date: ${DATE}
 */

class FSmanager {

    public List mountRO() throws IOException, RootToolsException, InterruptedException {
        List<String> output;
        output = RootTools.sendShell("/system/xbin/mount -o ro,remount -t yaffs2 /dev/block/mtdblock4 /system");
        return output;
    }

    public List mountRW() throws IOException, RootToolsException, InterruptedException {
        List<String> output;
        output = RootTools.sendShell("/system/xbin/mount -o rw,remount -t yaffs2 /dev/block/mtdblock4 /system");
        return output;
    }

    public void setSSHpasswordFileRW() throws IOException, InterruptedException, RootToolsException {
		RootTools.sendShell("/system/xbin/chmod +rw /etc/ssh/passwd");
    }

    public void setSSHpasswordFileRO() throws IOException, InterruptedException, RootToolsException {
		RootTools.sendShell("/system/xbin/chmod go-w /etc/ssh/passwd");
	}

    public void setDataIcon() throws IOException, RootToolsException, InterruptedException {
        String cmd1="sed -i \"s/ro.config.hw_opta=02/ro.config.hw_opta=224/g\" /system/build.prop";
		String cmd2="sed -i \"s/ro.config.hw_optb=0/ro.config.hw_optb=620/g\" /system/build.prop";
        RootTools.sendShell(cmd1);
        RootTools.sendShell(cmd2);


    }
        public void rmDataIcon() throws IOException, RootToolsException, InterruptedException {
                    String cmd1="sed -i \"s/ro.config.hw_opta=224/ro.config.hw_opta=02/g\" /system/build.prop";
					String cmd2="sed -i \"s/ro.config.hw_optb=620/ro.config.hw_optb=0/g\" /system/build.prop";
		RootTools.sendShell(cmd1);
        RootTools.sendShell(cmd2);

        }

}
