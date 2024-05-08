import random
while True:
        
    user_choice=int(input('''~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
    Welcome to Snake Water Gun
        Press  1 (for snake)
        Press  2 (for water)
        Press  3 (for gun)
        Press  4 (exit the game)
    />'''))
    if user_choice==4:
        break
    if user_choice not in [1,2,3,4]:
        print('invalid choice')
        continue
    user_choice=user_choice-1
    computer_choice=random.choice([0,1,2])

    # S=1
    # W=2
    # G=3

    #                 S W G
    # computer =      0 1 2
    # player =  S  0  D W L
    #           W  1  L D W
    #           G  2  W L D

    
    uc= 'Snake' if user_choice==0 else 'Water' if user_choice==1 else 'Gun' if user_choice==2 else ''
    cc= 'Snake' if computer_choice==0 else 'Water' if computer_choice==1 else 'Gun' if computer_choice==2 else ''

    print('your choice = ',uc)
    print('computer choice = ',cc)
    
    ans_mat=[[0,1,-1],
        [-1,0,1],
        [1,-1,0]]
    
    ans=ans_mat[user_choice][computer_choice]
    if ans==0:
        print("\n.......Draw!!.......\n")
    elif ans==-1:
        print("\n.......You Lose!!.......\n")
    elif ans==1:
        print("\n.......You Win!!.......\n")
    else :
        print("\nerror\n")

    