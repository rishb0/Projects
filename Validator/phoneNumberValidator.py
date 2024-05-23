import re
 
def isValidPhoneNumber(number):
    pattern = r'^(\+91[\-\s]?)?[6-9]\d{9}$'
    matches=re.match(pattern,number)
    if matches :
        return True
    else :
        return False
    
def main():
    while True:
        number = input('enter the number to verify:')
        if isValidPhoneNumber(number):
            print(number," is valid ")
        else :
            print("Not a valid number ")
    
if __name__=="__main__":
    main()
    
