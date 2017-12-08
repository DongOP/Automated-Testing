#coding:utf-8

import time
import sys
from com.android.monkeyrunner import MonkeyRunner ,MonkeyDevice ,MonkeyImage
from com.android.monkeyrunner.easy import EasyMonkeyDevice, By

# 确认年月日时分秒
now = time.strftime("%Y-%m-%d-%H-%M-%S")
# 指定要保存图片的位置和打印log的位置
path = 'E:\\monkeyrunner_file\\picture\\'
logpath = "E:\\monkeyrunner_file\\log\\"
# python中获取当前运行的文件的名字
name = sys.argv[0].split("\\")
filename = name[len(name) - 1]

print("The name of the file:%s" % name)
print("The name of script:%s" % filename)
# 新建一个log文件
log = open(logpath + filename[0:-3] + "_log_" + now +".txt", 'w')

device = MonkeyRunner.waitForConnection()
easyMonkey = EasyMonkeyDevice(device)

print("Pre-preparation is completed, start the app.")
runComponent = "dong.sqlite.order/.MainActivity"
# 启动首页Activity
device.startActivity(component = runComponent)
MonkeyRunner.sleep(2)

# 打印操作信息
log.write("The script starts to run\n")
# 截图
result = device.takeSnapshot()
# 保存截图 
result.writeToFile(path + "点击读取文件前".decode('utf-8') + now + '.png', 'png')

print("Click the read button")
by_read = By.id('id/btn_read')
easyMonkey.touch(by_read, MonkeyDevice.DOWN_AND_UP)
MonkeyRunner.sleep(1)
device.press('KEYCODE_BACK')
MonkeyRunner.sleep(1)

# 截取点击read后的屏幕图
result2 = device.takeSnapshot()
result2.writeToFile(path + "点击读取文件后".decode('utf-8') + now + ".png", 'png')

# 判断图片相识度是否是为90%
if(result.sameAs(result2, 0.9)):
    print("The main page picture comparison is successful")
    #打印信息到log文件
    log.write("The main page picture comparison is successful\n")
else:
    print("The main page picture comparison failed")
    log.write("The main page picture comparison failed\n")

log.write("The script test is over!")
print("The script test is over")