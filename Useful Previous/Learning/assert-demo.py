try:
    num = input("Enter a number:")
    assert (num == 10),"The number is not 10"

except AssertionError,msg:
    print msg
    print ("Sadly,num not equals to 10.")