package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Phonebook {
    private Map<String, Contact> contacts;
    private Scanner scanner;

    public Phonebook() {
        this.contacts = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }


    public Map<String, Contact> getData() {
        return contacts;
    }


    public void addContact(Contact contact) {
        contacts.put(contact.getCode(), contact); // Se usa el código del contacto como clave
    }


    public void deleteContact(String contactCode) {
        if (contacts.containsKey(contactCode)) {
            contacts.remove(contactCode);  // Eliminar el contacto usando su código
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("El contacto con el código " + contactCode + " no se encontró.");
        }
    }


    public void showPhonebook() {
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }
        for (Contact contact : contacts.values()) {
            contact.showContactDetails();
            System.out.println();
        }
    }


    public void displayMenu() {
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Seleccionar contacto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    showContacts();
                    break;
                case 3:
                    deleteContact();
                    break;
                case 4:
                    selectContact();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    private void addContact() {
        System.out.print("Ingrese el nombre: ");
        String firstName = scanner.nextLine();
        System.out.print("Ingrese los apellidos: ");
        String lastName = scanner.nextLine();
        System.out.print("Ingrese el número de teléfono: ");
        String phoneNumber = scanner.nextLine();
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        contacts.put(contact.getCode(), contact);
        System.out.println("Contacto añadido.");
    }


    private void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }
        for (Contact contact : contacts.values()) {
            contact.showContactDetails();
            System.out.println();
        }
    }


    private void deleteContact() {
        System.out.print("Ingrese el código del contacto a eliminar: ");
        String code = scanner.nextLine();
        if (contacts.containsKey(code)) {
            contacts.remove(code);
            System.out.println("Contacto eliminado.");
        } else {
            System.out.println("No se encontró el contacto.");
        }
    }


    private void selectContact() {
        System.out.print("Ingrese el código del contacto: ");
        String code = scanner.nextLine();
        if (contacts.containsKey(code)) {
            Contact contact = contacts.get(code);
            contactActionsMenu(contact);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    // Menú de acciones para un contacto

    private void contactActionsMenu(Contact contact) {
        while (true) {
            System.out.println("\nMenú de acciones para " + contact.getCode() + ":");
            System.out.println("1. Llamar a mi número");
            System.out.println("2. Llamar a otro número");
            System.out.println("3. Mostrar detalles");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    contact.callMyNumber();
                    break;
                case 2:
                    System.out.print("Ingrese el número al que desea llamar: ");
                    String number = scanner.nextLine();
                    contact.callOtherNumber(number);
                    break;
                case 3:
                    contact.showContactDetails();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}
