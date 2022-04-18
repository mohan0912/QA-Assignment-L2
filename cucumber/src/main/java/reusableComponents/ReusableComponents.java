package reusableComponents;
import java.awt.*;
import java.awt.datatransfer.Clipboard;    
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.devtools.v85.input.Input.DispatchKeyEventType;

public class ReusableComponents {
	public static void uploadFile(String path) throws InterruptedException, AWTException {
		
		path = path.replace("/","\\");
		//copy the string to clipboard memory
		StringSelection stringSelection = new StringSelection(path);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);
		Robot robot;
		
		
		try
		{
			robot = new Robot();
			Thread.sleep(5000);
			
			//key ctrl + v
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);		
			robot.waitForIdle();
			//key ctrl + o
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_O);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_O);
			robot.delay(500);
			
		}
		catch (Exception e) {
			e.printStackTrace();

		}

	}

}
