package org.example;
import java.sql.SQLOutput;
import java.util.*;
// My code actually technically does follow the Maven convention here as according to the maven convention we need to store different Classes in their own .java files,
// but I happen to have used only one class and the rest of the program runs on methods of that one class.
public class Main {
    List<String []> members = new ArrayList<>();
    List<String []> books = new ArrayList<>();
    List<String []> my_books = new ArrayList<>();
    Map<String, List<String[]>> records = new HashMap<>();
    Scanner scan = new Scanner(System.in);

    public void display_members(){
        for (String[] e : members) {
            System.out.println(Arrays.toString(e));
        }
    }
    public void register_member(){
        List<String []> issued_books = new ArrayList<>();
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Age: ");
        String age = scan.nextLine();
        System.out.print("Phone Number: ");
        String phone = scan.nextLine();
        System.out.println("Enter the number of issued books: ");
        Integer no_issued = scan.nextInt();
        System.out.print("Enter the amount of fine: ");
        Integer fine = scan.nextInt();
        while(no_issued>0){
            System.out.print("Enter the book name: ");
            String book_name = scan.nextLine();
            System.out.print("Enter the book author: ");
            String book_author = scan.nextLine();
            String[] issued_book_array = {book_name,book_author};
            no_issued--;
            issued_books.add(issued_book_array);
        }
        String[] member_array = new String[4];
        member_array[0] = name;
        member_array[1] = age;
        member_array[2] = phone;
        member_array[3] = fine+"";
        members.add(member_array);
        records.put(name,issued_books);
        display_members();
    }
    public void remove_member(){
        display_members();
        System.out.print("Enter index of member you want to remove : ");
        Integer index = scan.nextInt();
        members.remove(index);
        display_members();
    }
    public void display_books(){
        for (String[] book : books) {
            System.out.println("Book ID: " + book[0]);
            System.out.println("Title: " + book[1]);
            System.out.println("Author: " + book[2]);
            System.out.println("---------------------------");
        }
    }
    private int nextBookId = 1;
    public void add_book(){
        System.out.print("Book title: ");
        String title = scan.nextLine();
        System.out.print("Author: ");
        String author = scan.nextLine();
        System.out.print("Copies: ");
        Integer copies = scan.nextInt();
        String[] book_array = new String[3];
        Integer length = books.size();

        while(copies != 0) {
            book_array[0] = String.valueOf(nextBookId); // Use the next book ID
            book_array[1] = title;
            book_array[2] = author;
            books.add(book_array.clone()); // Clone the array to add a new instance
            nextBookId++; // Increment the next book ID
            copies--;
        }

        display_books();
    }


    public void remove_book(){
        display_books();
        System.out.print("Enter index of book you want to remove : ");
        Integer index = scan.nextInt();

        if (index >= 0 && index < books.size()) {
            books.remove(index);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Invalid index. Please try again.");
        }

        display_books();
    }

    public void issue_book(String member_name){
        display_books();
        System.out.print("Enter the Index of book to issue: ");
        Integer index_issue_book = scan.nextInt();
        records.put(member_name, Collections.singletonList(books.get(index_issue_book)));
        books.remove(index_issue_book);

    }
    public void return_book(String member_name){
        add_book();
        records.remove(member_name);
    }
    public void librarian() {
        while (true) {
            System.out.println("-------------------------------");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("-------------------------------");

            Integer choice = scan.nextInt();

            switch (choice) {
                case 1:
                    register_member();
                case 2:
                    remove_member();
                case 3:
                    add_book();
                case 4:
                    remove_book();
                case 5:
                    display_members();
                case 6:
                    display_books();
                case 7:
                    choose();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void member(){
        System.out.print("Name: ");
        String member_name = scan.nextLine();
        System.out.print("Phone No: ");
        String member_Phone_no = scan.nextLine();
        while (true) {
            System.out.printf("Welcome %c \n",member_name);
            System.out.println("-------------------------------");
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("-------------------------------");

            Integer choice = scan.nextInt();

            switch (choice) {
                case 1:
                    display_books();
                case 2:
                    records.get(member_name);
                case 3:
                    issue_book(member_name);
                case 4:
                    return_book(member_name);
                case 5:
                    for(String[] e : members){
                        if (e[0] == member_name){
                            System.out.println(e[3]);
                        }
                    }
                case 6:
                    choose();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
    public void choose(){
        while (true) {
            System.out.println("Library Portal Initialized….");
            System.out.println("-------------------------------");
            System.out.println("Enter as a librarian");
            System.out.println("Enter as a member");
            System.out.println("Exit");
            System.out.println("-------------------------------");

            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    librarian();
                case 2:
                    member();
                case 3:
                    System.out.println("Exiting the program.");
                    scan.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void librarianActions() {
        while (true) {
            System.out.println("-------------------------------");
            System.out.println("1. Register a member");
            System.out.println("2. Remove a member");
            System.out.println("3. Add a book");
            System.out.println("4. Remove a book");
            System.out.println("5. View all members along with their books and fines to be paid");
            System.out.println("6. View all books");
            System.out.println("7. Back");
            System.out.println("-------------------------------");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    register_member();
                    break;
                case 2:
                    remove_member();
                    break;
                case 3:
                    add_book();
                    break;
                case 4:
                    remove_book();
                    break;
                case 5:
                    display_members();
                    break;
                case 6:
                    display_books();
                    break;
                case 7:
                    chooseRole();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void memberActions(String memberName) {
        while (true) {
            System.out.printf("Welcome %s\n", memberName);
            System.out.println("-------------------------------");
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue book");
            System.out.println("4. Return book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("-------------------------------");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    display_books();
                    break;
                case 2:
                    display_books();
                    break;
                case 3:
                    issue_book(memberName);
                    break;
                case 4:
                    return_book(memberName);
                    break;
                case 5:
                    for(String[] e : members){
                        if (e[0] == memberName){
                            System.out.println(e[3]);
                        }
                    }
                    break;
                case 6:
                    chooseRole();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void chooseRole() {
        while (true) {
            System.out.println("Library Portal Initialized….");
            System.out.println("-------------------------------");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            System.out.println("-------------------------------");

            int choice = scan.nextInt();
            scan.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    librarianActions();
                    break;
                case 2:
                    System.out.print("Name: ");
                    String memberName = scan.nextLine();
                    System.out.print("Phone No: ");
                    String memberPhoneNo = scan.nextLine();
                    memberActions(memberName);
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    scan.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Main library = new Main();
        library.chooseRole();
    }
}
