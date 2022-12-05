package com.epammurodil.controller.command;

import com.epammurodil.controller.command.impl.*;

public enum CommandType {
    DEFAULT(new DefaultCommand()),
    SIGN_UP(new SignUpCommand()),
    NEW_MEDICINE(new StartNewMedicine()),
    ADD_MEDICINE(new FinishNewMedicineCommand()),
    MEDICINE(new MedicinePageCommand()),
    NOT_FOUND(new NotFoundCommand()),
    RATE_MEDICINE(new MedicineRatingCommand()),
    LOGOUT(new LogOutCommand()),
    DELETE_MEDICINE(new DeleteMedicineCommand()),
    ACCOUNTS(new AccountsCommand()),
    MAKE_ORDER(new MakeOrderCommand()),
    ORDERS(new OrdersCommand()),
    RECEIPTS(new ReceiptsCommand()),
    SIGN_IN(new SignInCommand());
    private Command command;

    CommandType(Command command){
        this.command = command;
    }

    public static Command define(String commandStr){

        CommandType commandType;
        try {
            commandType = commandStr != null ? CommandType.valueOf(commandStr.toUpperCase()) : DEFAULT;
        } catch (IllegalArgumentException e){
            commandType = NOT_FOUND;
        }
        return commandType.command;
    }

    public static CommandType defineCommandType(String commandStr) {

        CommandType commandType;
        try {
            commandType = commandStr != null ? CommandType.valueOf(commandStr.toUpperCase()) : DEFAULT;
        } catch (IllegalArgumentException e){
            commandType = DEFAULT;
        }

        return commandType;
    }
}