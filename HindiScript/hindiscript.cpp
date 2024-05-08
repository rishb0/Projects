#include <iostream>
#include <fstream>
#include <string>
#include <limits>    // for using limits in ignore()
#include <windows.h> // for messageBeep     Sleep()
#include <csignal>   // for Signal()

#ifdef max
#undef max
#endif

#define RESET "\033[0m"
#define RED "\033[31m"
#define BOLD "\033[1m"
#define LIGHT_GREEN "\033[32m"
#define GREEN "\033[92m"
#define BBLACK "\033[90m"
#define YELLOW "\033[33m"
#define BLUE "\033[34m"
#define MAGENTA "\033[35m"
#define CYAN "\033[36m"
#define WHITE "\033[37m"

using namespace std;

void welcomeScreen();
void loadingMessage();
void terminal();
int compiler();
string translater(string);
void executer();
void introScreen();
void load();
void signalHandler(int);

void signalHandler(int signum)
{

    if (signum == SIGINT)
    {
        // removing these temp files , if they user press clt c and they got undeleted 
        remove("error_logFile.txt");
        remove("deletethis.exe");
        remove("deletethis.cpp"); 

        cout << RESET; // reseting the terimanl color to default
        exit(0); // Exit gracefully after removing the file
    }
}

void load()
{
    cout << GREEN << "\n_______________________________________" << RESET << endl;
    cout << RED << "\n_______________________________________" << RESET << endl;
    // cout << YELLOW << "\n_______________________________________" <<RESET <<endl;
    cout << BBLACK << "\n_______________________________________" << RESET << endl;
    // cout << BLUE << "\n_______________________________________" <<RESET <<endl;
    // cout << MAGENTA << "\n_______________________________________" <<RESET <<endl;
    // cout << CYAN << "\n_______________________________________" <<RESET <<endl;
    // cout << WHITE << "\n_______________________________________" << RESET << endl;
    cout << BOLD << "\n_______________________________________" << RESET << endl;
    // cout << LIGHT_GREEN << "\n_______________________________________" << RESET << endl;

    system("cls");
    // std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');  // ignore maximum possible  char from buffer until newline
}

void introScreen()
{
    cout << GREEN << "\n________________________________________________________________________________________\n________________________________________________________________________________________\n\n"
         << RESET;
    // Sleep(50);
    // cout << "   _____  ____  _   _ _____    _____  _____ _____  _____ _____ _______  " << endl;
    // Sleep(50);
    // cout << "  / ____|/ __ \\| \\ | |_   _|  / ____|/ ____|  __ \\|_   _|  __ \\__   __| " << endl;
    // Sleep(50);
    // cout << " | (___ | |  | |  \\| | | |   | (___ | |    | |__) | | | | |__) | | |    " << endl;
    // Sleep(50);
    // cout << "  \\___ \\| |  | | . ` | | |    \\___ \\| |    |  _  /  | | |  ___/  | |    " << endl;
    // Sleep(50);
    // cout << "  ____) | |__| | |\\  |_| |_   ____) | |____| | \\ \\ _| |_| |      | |    " << endl;
    // Sleep(50);
    // cout << " |_____/ \\____/|_| \\_|_____| |_____/ \\_____|_|  \\_\\_____|_|      |_|    " << endl;
    // Sleep(50);

    // cout << "   _____             _    _____           _       _   " << endl;
    // cout << "  / ____|           (_)  / ____|         (_)     | |  " << endl;
    // cout << " | (___   ___  _ __  _  | (___   ___ _ __ _ _ __ | |_ " << endl;
    // cout << "  \\___ \\ / _ \\| '_ \\| |  \\___ \\ / __| '__| | '_ \\| __|" << endl;
    // cout << "  ____) | (_) | | | | |  ____) | (__| |  | | |_) | |_ " << endl;
    // cout << " |_____/ \\___/|_| |_|_| |_____/ \\___|_|  |_| .__/ \\__|" << endl;
    // cout << "                                           | |        " << endl;
    // cout << "                                           |_|        " << endl;

    // cout << "                                         _             ____  _     _           _     _      " << endl;
    // Sleep(50);
    // cout << "                                        | |__  _   _  |  _ \\(_)___| |__   __ _| |__ | |__   " << endl;
    // Sleep(50);
    // cout << "                                        | '_ \\| | | | | |_) | / __| '_ \\ / _` | '_ \\| '_ \\  " << endl;
    // Sleep(50);
    // cout << "                                        | |_) | |_| | |  _ <| \\__ \\ | | | (_| | |_) | | | | " << endl;
    // Sleep(50);
    // cout << "                                        |_.__/ \\__, | |_| \\_\\_|___/_| |_|\\__,_|_.__/|_| |_| " << endl;
    // Sleep(50);
    // cout << "                                               |___/                                        " << endl;
    // Sleep(50);
    // cout << ".|'''|                          .|'''|                              ||    " << endl;
    // cout << "||                       ''     ||                    ''            ||    " << endl;
    // cout << "`|'''|, .|''|, `||''|,   ||     `|'''|, .|'', '||''|  ||  '||''|, ''||''  " << endl;
    // cout << " .   || ||  ||  ||  ||   ||      .   || ||     ||     ||   ||  ||   ||    " << endl;
    // cout << " |...|' `|..|' .||  ||. .||.     |...|' `|..' .||.   .||.  ||..|'   `|..' " << endl;
    // cout << "                                                           ||             " << endl;
    // cout << "                                                          .||             " << endl;

    // cout << "             '||                  '||''|.    ||         '||              '||      '||      " << endl;
    // cout << "              || ...  .... ...     ||   ||  ...   ....   || ..    ....    || ...   || ..   " << endl;
    // cout << "              ||'  ||  '|.  |      ||''|'    ||  ||. '   ||' ||  '' .||   ||'  ||  ||' ||  " << endl;
    // cout << "              ||    |   '|.|       ||   |.   ||  . '|..  ||  ||  .|' ||   ||    |  ||  ||  " << endl;
    // cout << "              '|...'     '|       .||.  '|' .||. |'..|' .||. ||. '|..'|'  '|...'  .||. ||. " << endl;
    // cout << "                      .. |                                                                 " << endl;
    // cout << "                       ''                                                                  " << endl;

    Sleep(50);
    cout << GREEN << " _    _ _           _ _    _____           _       _                        " << RESET << endl;
    Sleep(50);
    cout << GREEN << "| |  | (_)         | (_)  / ____|         (_)     | |                       " << RESET << endl;
    Sleep(50);
    cout << GREEN << "| |__| |_ _ __   __| |_  | (___   ___ _ __ _ _ __ | |_                      " << RESET << endl;
    Sleep(50);
    cout << GREEN << "|  __  | | '_ \\ / _` | |  \\___ \\ / __| '__| | '_ \\| __|                     " << RESET << endl;
    Sleep(50);
    cout << GREEN << "| |  | | | | | | (_| | |  ____) | (__| |  | | |_) | |_                      " << RESET << endl;
    Sleep(50);
    cout << GREEN << "|_|  |_|_|_| |_|\\__,_|_| |_____/ \\___|_|  |_| .__/ \\__|                     " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                                            | |                             " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                                            |_|                           " << RESET << endl;

    Sleep(50);
    cout << GREEN << "                                _         _          _     _           _ _  " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                               | |       (_)        | |   (_)         | (_) " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                   ___ ___   __| | ___    _ _ __    | |__  _ _ __   __| |_  " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                  / __/ _ \\ / _` |/ _ \\  | | '_ \\   | '_ \\| | '_ \\ / _` | | " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                 | (_| (_) | (_| |  __/  | | | | |  | | | | | | | | (_| | | " << RESET << endl;
    Sleep(50);
    cout << GREEN << "                  \\___\\___/ \\__,_|\\___|  |_|_| |_|  |_| |_|_|_| |_|\\__,_|_| " << RESET << endl;
    Sleep(50);

    cout << GREEN << "________________________________________________________________________________________\n________________________________________________________________________________________\n"
         << RESET << endl;
    // Sleep(50);

    // getch();
    cout << BBLACK << "\n [ ENTER dabao ] " << RESET;

    std::cin.ignore(numeric_limits<streamsize>::max(), '\n'); // ignore maximum possible  char from buffer until newline
}

void welcomeScreen()
{
    Sleep(100);
    cout << GREEN << "\n----------------------------------------------------------------------------------\n"
         << RESET;

    // Sleep(15);
    // std::cout << "        _   _      _ _ " << std::endl;
    // Sleep(15);
    // std::cout << "       | | | |    | | |" << std::endl;
    // Sleep(15);
    // std::cout << "       | |_| | ___| | | ___" << std::endl;
    // Sleep(15);
    // std::cout << "       |  _  |/ _ \\ | |/ _ \\" << std::endl;
    // Sleep(15);
    // std::cout << "       | | | |  __/ | | (_) |" << std::endl;
    // Sleep(15);
    // std::cout << "       |_| |_|\\___|_|_|\\___/" << std::endl
    //           << endl;
    // Sleep(15);

    Sleep(15);
    cout << GREEN << "  _   _                           _        " << RESET << endl;
    Sleep(15);
    cout << GREEN << " | \\ | |                         | |       " << RESET << endl;
    Sleep(15);
    cout << GREEN << " |  \\| | __ _ _ __ ___   __ _ ___| |_ ___  " << RESET << endl;
    Sleep(15);
    cout << GREEN << " | . ` |/ _` | '_ ` _ \\ / _` / __| __/ _ \\ " << RESET << endl;
    Sleep(15);
    cout << GREEN << " | |\\  | (_| | | | | | | (_| \\__ \\ ||  __/ " << RESET << endl;
    Sleep(15);
    cout << GREEN << " |_| \\_|\\__,_|_| |_| |_|\\__,_|___/\\__\\___| " << RESET << endl
         << endl;
    Sleep(15);
    cout << GREEN << "" << RESET << endl;

    // cout<<"      ___       __   __         ___ "<<endl;
    // cout<<"|  | |__  |    /  ` /  \\  |\\/| |__  "<<endl;
    // cout<<"|/\\| |___ |___ \\__, \\__/  |  | |___ "<<endl<<endl;

    // cout << "               _                      \n";
    // cout << "              | |                     \n";
    // cout << " __      _____| | ___  _ __ ___   ___ \n";
    // cout << " \\ \\ /\\ / / _ \\ |/ _ \\| '_ ` _ \\ / _ \\\n";
    // cout << "  \\ V  V /  __/ | (_) | | | | | |  __/\n";
    // cout << "   \\_/\\_/ \\___|_|\\___/|_| |_| |_|\\___|\n";
    // cout << "                                      \n";
    // cout << "                                    \n";

    cout << GREEN << "/> code likhna shuru kro  " << BBLACK << "          [ run krne ke liye 2 baar ENTER dabao ] \n"
         << RESET;
}

void loadingMessage()
{
    system("cls");

    Sleep(40);
    cout << GREEN << "l p!  w # j   h   L p !   {   h     # l   e   h   h   c   # h !   x c  sc" << RESET << endl;
    Sleep(40);
    cout << GREEN << "o l?  a i a   i h o r }       i w   $   p r   i h a   o   i i }   " << WHITE << "x" << GREEN << " o  t" << WHITE << "#" << RESET << endl;
    Sleep(40);
    cout << GREEN << "a e:  r n v   n i a o ~   #   n e     o l r   n i c   a   n n ~     p  a " << RESET << endl;
    Sleep(40);
    cout << GREEN << "d a|  m c " << WHITE << "a" << GREEN << "   d n d   $       d l   ^   e o   d n k   d   c d #   e y  yj" << RESET << endl;
    Sleep(40);
    cout << GREEN << "i s~  i l     i d i c ^   f   " << WHITE << "i" << GREEN << " c   # a a r   i d e   e   l i ^   r r   s" << RESET << endl;
    Sleep(40);
    cout << GREEN << "n e#  n u s     i n o (   u     0   *   s       ! d   " << WHITE << "r" << GREEN << "   u   [   r i  t " << RESET << endl;
    Sleep(40);
    cout << GREEN << "" << WHITE << "g" << GREEN << "  #  g d c   $ s g d &   n   s m   ( d e ?   s           d s $   o g  uc" << RESET << endl;
    Sleep(40);
    cout << GREEN << "  w" << WHITE << "$" << GREEN << "    e r   c c   e #   c   c e   -   u ?   c $ t   p   e c #   r h  n+" << RESET << endl;
    Sleep(40);
    cout << GREEN << "f a   u   i   r r f " << WHITE << "r" << GREEN << " @   t   r     [ i   ?   r c h r r   r   |     t  e+" << RESET << endl;
    Sleep(40);
    cout << GREEN << "i i   p c p   i i i   =   i   i u   2   S ?   i r i   o   c i -   #    d " << RESET << endl;
    Sleep(40);
    cout << GREEN << "l " << WHITE << "t" << GREEN << "     + " << WHITE << "t" << GREEN << "   p p l   >   o   p s   ? n   ?   p i " << WHITE << "s" << GREEN << "   g   + p =   ~ c   j" << RESET << endl;
    Sleep(40);
    cout << GREEN << "e     c " << WHITE << "+" << GREEN << "     " << WHITE << "t " << GREEN << "t" << GREEN << " e   .   n   " << WHITE << "t" << GREEN << " e   [   " << WHITE << "E" << GREEN << " " << WHITE << "?" << GREEN << "   " << WHITE << "t" << GREEN << " p     r   " << WHITE << "+" << GREEN << " " << WHITE << "t" << GREEN << " .   X p  ia" << RESET << endl;
    Sleep(40);
    cout << GREEN << "" << WHITE << "s" << GREEN << "     p           " << WHITE << "s" << GREEN << "   " << WHITE << "#" << GREEN << "   a     " << WHITE << "r" << GREEN << "   . " << WHITE << "g" << GREEN << "         " << WHITE << "t" << GREEN << "     a       " << WHITE << ">" << GREEN << "   x " << WHITE << "p" << GREEN << "   v" << RESET << endl;
    Sleep(40);
    cout << GREEN << "      " << WHITE << "u" << GREEN << "                   " << WHITE << "l" << GREEN << "         " << WHITE << ")" << GREEN << "                 " << WHITE << "m" << GREEN << "           " << WHITE << "x" << GREEN << "     " << WHITE << "a" << GREEN << RESET << endl;
    Sleep(40);
    // Sleep(5);
    system("cls");
    // std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
}

void terminal()
{
    // defining string for initial code
    string initialCode = "\n#include <iostream> \n#include <cstdio>\n\n using namespace std;\n  int main(){ \n";

    // defining string for end code
    string endCode = "\nreturn 0; \n}";

    // defining string for mid code
    string midCode;

    string fullCode = initialCode; // adding initial code to ful code  ;

    int enterCount = 0;       // variable to count number of ENTER pressed
    int isInformedToExit = 0; //

    do
    {
        string line; // temperary var
        cout << GREEN << "> " << RESET;
        getline(cin, line); // this will store the line entered by user in midCode variable
        midCode += line;    /// adding line entered by user to fullcode as midcode
        midCode += "\n";

        if (line.empty())
        {
            enterCount++;
        }
        else
            enterCount = 0;

    } while (enterCount != 2); // running loop until you pressed ENTER twice

    midCode = translater(midCode); // call to translator

    fullCode += midCode; // adding mid code to full code
    fullCode += endCode; // adding end code to full code

    // opening  src file
    ofstream fout;
    fout.open("deletethis.cpp");

    // writing to src file
    fout << fullCode;

    fout.close();
}

int compiler()
{

    loadingMessage();
    // compiling the cpp file and storing there
    string sourceFileName = "deletethis.cpp";
    string outputFileName = "deletethis.exe";

    // this is the name of a file which wil hold the errorcode displayed by compiler on terminal
    string errorFileName = "error_logFile.txt"; //  we use ( . ) dot before file name to make it hidden

    // building thge compile command for terminal  "g++ filename.cpp -o filename.exe 2> errorfile.txt"
    string compileCommand = "g++ ";
    compileCommand += sourceFileName;
    compileCommand += " -o ";
    compileCommand += outputFileName;
    compileCommand += " 2> "; // this is teminal command which send the error message to a file after compilation error
    compileCommand += errorFileName;

    // Compile the source file using the system command
    int compilationResult = system(compileCommand.c_str()); // c_str is used to convert stirng to c style char array end with null character ,  bcz system() uses a c style array r char with null character at end (a c sting) so we us this funcitoln c_str to convert the cpp sting to c style string
    // Sleep(1000);
    // getchar();
    // removing src file
    // remove(sourceFileName.c_str());

    if (compilationResult == 0)
    {
        // remove(errorFileName.c_str()); //   this will delete the error file ,as it's work is no more
    }
    else
    {
        MessageBeep(MB_ICONERROR);
        cout << BBLACK << "\n\n ------------------------------------------------" << RESET;
        cout << RED << "\n Are! Bhai Bhai Bhai.......    | syntax error    " << RESET << endl;
        cout << BBLACK << "\n ------------------------------------------------\n"
             << RESET;
        cout << BBLACK << "\n [ ENTER dabao ] " << RESET;

        // remove(errorFileName.c_str()); //   this will delete the error file ,as it's work is no more

        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
        system("cls");
    }

    return !compilationResult;
}

string translater(string midCode)
{

    // string midCode ;
    string cppWords[] = {"if", "else", "true", "false", "cout<<", "cin>>", "while", "do", "break", "continue", "endl"};

    string myWords[] = {"agar", "nahi to", "sahi", "galat", "likho", "padho", "jab tak", "karo", "ruko", "firse", "nai line"};

    for (int i = 0; i < sizeof(cppWords) / sizeof(cppWords[0]); i++)
    {
        int pos = 0;
        while (pos != -1)
        {
            pos = midCode.find(myWords[i]);

            if (pos != -1)
            {

                bool isUnderQuotes = false;
                char charQuot = '"';

                if (i == 4) // for PRINT to handle "this is a =",a;   -> cout<<"thisisa= "<<a;
                {
                    int j = pos + myWords[i].length();
                    while (midCode[j] != '\n' && j < midCode.length())
                    {

                        if (midCode[j] == charQuot && isUnderQuotes == false)
                        {
                            isUnderQuotes = true;
                        }
                        else if (midCode[j] == charQuot && isUnderQuotes == true)
                        {
                            isUnderQuotes = false;
                        }

                        if (isUnderQuotes == false && midCode[j] == ',')
                        {
                            // Replace the comma with '<<'
                            midCode.replace(j, 1, "<<");
                            j++;
                        }
                        j++;
                    }
                }
                else if (i == 5) // fro cin to handle >>    cin>>a >> b   - >   input a, b;
                {

                    int j = pos + myWords[i].length();
                    while (midCode[j] != '\n' && j < midCode.length())
                    {
                        if (midCode[j] == ',')
                        {
                            midCode.replace(j, 1, ">>");
                            j++;
                        }
                        j++;
                    }
                }

                midCode.replace(pos, myWords[i].length(), cppWords[i]);
                pos += cppWords[i].length();
            }
        }
    }

    if (1) // for replacing semicolon with fullstop
    {
        bool isUnderQuotes = false;
        char charQuot = '"';
        int j = 0;
        while (j < midCode.length())
        {

            if (midCode[j] == charQuot && isUnderQuotes == false)
            {
                isUnderQuotes = true;
            }
            else if (midCode[j] == charQuot && isUnderQuotes == true)
            {
                isUnderQuotes = false;
            }

            if (isUnderQuotes == false && midCode[j] == '.')
            {
                // Replace the | with ;
                midCode[j] = ';';
            }

            j++;
        }
    }

    return midCode;
}

void executer()
{
   
    // success compile and output  sound
    MessageBeep(MB_ICONWARNING);

    cout << BBLACK << "\n\n------------------------    OUTPUT    -----------------------\n\n"
         << RESET;

    cout << BOLD;
    system("deletethis.exe");
    cout << RESET;
    cout << BBLACK << "\n\n------------------------------------------------------------\n\n"
         << RESET;

    // removing output
    remove("deletethis.exe");

    cout << BBLACK << "\n [ ENTER dabao ] " << RESET;

    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    // getch();
    system("cls");
}

int main()
{
 // installing signalhandler funciton to handle the clt c
    // signal(SIGINT, signalHandler);

    load();

    introScreen(); // this displays a Hindi script logo
    while (1)
    {
        welcomeScreen(); // display namste
        terminal();      // run terminal     

        int isCompiled = compiler();        

        if (isCompiled)
            executer();        
    }

    return 0;

    // 328
    // 332
    // 343
    // 476


}

/*
    1)to print to console  use :-
      //use  'change line' to change line  ;

      int a=10,b=5,c=3;
      print b+c;

      print "this is line 1",  change line , "this is line 2";

      print change line ;

      print"value of a = ", a ;

    2)to take input form keyboard:-


     int a;
     input a ;
     print a;


    3)you can use confiitonal statements like if else by :-

        agar (a%2==0)                   agar (a%2==0) {
            print"even";                    print"even";
        nahi to                or         }
            print"odd";                 nahi to {
                                            print "odd\n" ;
                                        }
    4) use can define boolean word like true false as:-

        boolean var1 = sahi ;
        boolean var2 = galat ;
        boolean var3 = null;

        agar(var1 == sahi )
            print " yes true ";

    5) you can use loops like whilelop as :-
        boolean var1 = sahi
        int i = 0;
        jab tak (var1 == sahi )
        {
            print "i am working";
            i++;

            agar ( i == 3)
             var1  = galat;
            else
             var1 = sahi;
        }

    6) somethingg like do while loop :-
        int a =0;
        karo
        {
            print a ;
            a++;
        }jab tak(a<10);

    7) you can use break and continue as :-
        int i = 1;
        jab tak ( sahi)
        {

            agar (i==2)
            {
                exit loop     // for break;
            }
            agar (i==1)
            {
                i++;
                next iteration ;
            }

            print i ;
            i++;
        }

 */
/*

       _   _  _   _  _  __     ___                      _   _   _   _   __ ___                      __
  /\  |_) /  | \ |_ |_ /__ |_|  |    | |/ |  |\/| |\ | / \ |_) / \ |_) (_   | | | \  / \    / \/ \_/ /
 /--\ |_) \_ |_/ |_ |  \_| | | _|_ \_| |\ |_ |  | | \| \_/ |   \_X | \ __)  | |_|  \/   \/\/  /\  | /_


 */
/*
print "HELLO";       // this will print  Hello


int var=5;
print var ;             // this vill print 5    , 'var'can be  of any type

print "the value is " , var , change line ;         //  output :- the value is 5


*/
