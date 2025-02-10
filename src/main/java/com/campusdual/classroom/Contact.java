package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contact implements ICallActions {

    private String name;
    private String surname;
    private String phone;
    private String code;
    private List<String> otherPhoneNumbers; // bonus



    public Contact (String name, String surName, String phoneNumber){
        this.name = name;
        this.surname = surName;
        this.phone = phoneNumber;
        this.code = generateCode( name,surName);
        this.otherPhoneNumbers = new ArrayList<>();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    public void setOtherPhoneNumbers(List<String> otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public String getSurName() {
        return surname;
    }

    public void setSurName(String surName) {
        this.surname = surName;
    }



    public String getSurnames() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }


    public void addPhoneNumber(String number) { // bonus
        otherPhoneNumbers.add(number);
    }

    public String getPhoneNumber() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void callMyNumber() {
        System.out.println("Llamando a mi propio número: " + phone);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + surname);
        System.out.println("Número de teléfono: " + phone);

    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("Llamando al número: " + number);
        System.out.println("Nombre: " + name);
        System.out.println("Apellido: " + surname);
        System.out.println("Número al que se llama: " + number);

    }

    @Override
    public void showContactDetails() {
        System.out.println("Contacto: " + name + " " + surname);
        System.out.println("Número principal: " + phone);
        System.out.println("Código: " + code);
        if (!otherPhoneNumbers.isEmpty()) {
            System.out.println("Otros números: " + String.join(", ", otherPhoneNumbers));
        }
    }

    private String generateCode(String name, String surname) {
        // Normalizar y pasar  minúsculas
        String nameInitial = name.substring(0, 1).toLowerCase();
        String normalizedSurName = Normalizer.normalize(surname, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")  // Eliminar acentos y tildes
                .toLowerCase();

        // Si el apellido contiene espacios, significa que tiene más de un apellido
        if (normalizedSurName.contains(" ")) {
            // Dividimos los apellidos
            String[] surNames = normalizedSurName.split(" ");

            //  la inicial del nombre + inicial del primer apellido
            StringBuilder code = new StringBuilder(nameInitial + surNames[0].substring(0, 1).toLowerCase());

            // Si hay más de un apellido, tomamos el segundo apellido completo
            if (surNames.length > 1) {
                for (int i = 1; i < surNames.length; i++) {
                    code.append(surNames[i]);
                }
            }

            return code.toString();
        } else {
            return nameInitial + normalizedSurName;
        }
    }




}








