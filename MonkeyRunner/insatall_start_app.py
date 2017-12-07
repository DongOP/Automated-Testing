#coding:utf-8

from com.android.monkeyrunner import MonkeyRunner, MonkeyDevice

device = MonkeyRunner.waitForConnection()
# 安装apk
device.installPackage("C:\\Users\Administrator\\Desktop\Automated-Testing\\Apks\\SQLiteOrder.apk")
MonkeyRunner.sleep(1)

print ("Installation is complete, start the app.")
runComponent = "dong.sqlite.order/.MainActivity"
# 启动首页Activity
device.startActivity(component=runComponent)