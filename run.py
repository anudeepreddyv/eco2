
import argparse

from src.gspread_api import get_worksheet_api_service
from src.mail import start_server,login
from src.sgp30_api import get_info


def main(timer,limit,email):
    """
    This method is used to call both gspread api and sgp30 api

    Returns:
        int: 0 Always for Success.1 for Failure.
    """
    try:
        mail_server = start_server()
        from_mail,password = "username@gmail.com","password"
        login(mail_server,from_mail,password)
        worksheet_instance = get_worksheet_api_service()
        get_info(mail_server,worksheet_instance,timer,limit,from_mail,email)
    except Exception as e:
        print("Error : \n{}".format(e))
        return 1

    return 0

if __name__ == "__main__":
    # Initialize parser
    msg = "This script is used to calculate CO2 levels."
    parser = argparse.ArgumentParser(msg)
    # Adding optional argument
    parser.add_argument("-u", "--Timer", help = "Timer to append co2 levels in google spreadsheet.")
    parser.add_argument("-co2", "--Limit", help = "Limit of co2 level to send a reminder through email.")
    parser.add_argument("-email", "--Email", help = "Reminder email.")
    args = parser.parse_args()
    main(args.Timer,args.Limit,args.Email)