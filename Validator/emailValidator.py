import re
 
def isValidEmail(email):
    pattern = r'^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'

    matches=re.match(pattern,email)
    if matches :
        return True
    else :
        return False
    
def main():
    while True:
        email = input('enter the email to verify:')
        if isValidEmail(email):
            print(email," is valid ")
        else :
            print("Not a valid email")
        
    
    
if __name__=="__main__":
    main()
    
