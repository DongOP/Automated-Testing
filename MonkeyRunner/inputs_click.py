#coding:utf-8

from com.android.monkeyrunner.easy import EasyMonkeyDevice, By  
from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice  
  
def testlogin():  
    import codecs  
    codecs.register(lambda name: codecs.lookup('utf-8') if name == 'cp65001' else None)  
  
    device = MonkeyRunner.waitForConnection()  
    easyMonkey = EasyMonkeyDevice(device)  
  
    print ("Test start")  
    # 启动应用Activity
    device.shell('am start dong.sqlite.order/dong.sqlite.order.MainActivity')  
  
    MonkeyRunner.sleep(3)  
  
    print ("Start typing user name and password")
    by_name = By.id("id/username")
    # print (easyMonkey.visible(by_name))  
    easyMonkey.type(by_name,'zdd')  
    MonkeyRunner.sleep(1)  
  
    by_psd = By.id('id/userpwd')
    # print (easyMonkey.visible(by_psd))  
    easyMonkey.type(by_psd,"my_psd")  
    MonkeyRunner.sleep(1)  
    # device.press('KEYCODE_ENTER')  
    # MonkeyRunner.sleep(1)  
    device.press('KEYCODE_BACK')  
    MonkeyRunner.sleep(1)  
  
    by_save = By.id('id/btn_save')  
    # print (easyMonkey.visible(by_save))  
    easyMonkey.touch(by_save,MonkeyDevice.DOWN_AND_UP)  
    MonkeyRunner.sleep(2)

    print ("Click the read button")
    by_read = By.id('id/btn_read')  
    # print (easyMonkey.visible(by_read))  
    easyMonkey.touch(by_read,MonkeyDevice.DOWN_AND_UP)  
    MonkeyRunner.sleep(1) 
  
    device.press('KEYCODE_BACK')
    print ("Test end")

if __name__ == '__main__':
	testlogin()