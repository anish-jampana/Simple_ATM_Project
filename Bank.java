/**
 * Write a description of class Bank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList; 
import java.util.Random;
public class Bank
{
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    
    /**
     * Create a new bank object with empty lists of users and accounts
     * @param name - the name of the bank
     */
    public Bank(String setName) {
        this.name = setName;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Generate a new universally unique ID for a user.
     * @return the uuid
     */
    public String getNewUserUUID() {
        // initializations
        String uuid;
        Random random = new Random();
        int length = 6;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            // generate the number
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (User user : this.users) {
                if (uuid.compareTo(user.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } 
        while (nonUnique);

        return uuid;
    }

    /**
     * Generate a new universally unique ID for an account
     * @return the uuid
     */
    public String getNewAccountUUID() {
        // initializations
        String uuid;
        Random random = new Random();
        int length = 10;
        boolean nonUnique;

        // continue looping until we get a unique ID
        do {
            // generate the number
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it's unique
            nonUnique = false;
            for (Account account : this.accounts) {
                if (uuid.compareTo(account.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }
        } 
        while (nonUnique);

        return uuid;
    }

    /**
     * Add an account
     * @param anAcct - the account to add
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /**
     * Create a new user of the bank
     * @param first - the user's first name
     * @param last - the user's last name
     * @param pin - the user's pin
     * @return the new User object
     */
    public User addUser(String first, String last, String pin) {
        //create a new User object and add it to our list.
        User object = new User(first, last, pin, this);
        this.users.add(object);

        // create a savings account for the user and add to User and Bank accounts list
        Account account = new Account("Savings", object, this);
        object.addAccount(account);
        this.accounts.add(account);

        return object;
    }
    
    /**
     * Get the user object associated with a particular userID and pin, if they are valid
     * @param userID - the UUID of the user to login
     * @param pin - the pin of the user
     * @return the User object, if the login is successful, or null, if it is not
     */
    public User userLogin(String userID, String pin) {
        // search through list of users
        for (User user : this.users) {
            // check user ID is correct
            if (user.getUUID().compareTo(userID) == 0 && user.validatePin(pin)) { 
                return user;
            }
        }
        
        // if we haven't found the user or have an incorrect pin
        return null;
    }
    
    /**
     * Get the name of the bank
     * @return the name of the bank
     */
    public String getName() {
        return this.name;
    }
}

