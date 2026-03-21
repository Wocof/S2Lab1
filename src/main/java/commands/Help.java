package commands;

public class Help implements ExecuteCommand {

    @Override
    public boolean executeCommand() {
        System.out.println("Доступные команды:");
        System.out.println("help - получение информации о доступных командах");
        System.out.println("exit - выход из программы");
        System.out.println("unit_add - добавление новой единицы");
        System.out.println("unit_list - список всех доступных единиц");
        System.out.println("unit_show - найти единицу по уникальному номеру");
        System.out.println("unit_update - обновить информацию об единице");
        System.out.println("unit_delete - удалить единицу");
        System.out.println("conv_add - добавление нового правила конвертации");
        System.out.println("conv_list - список всех доступных правил конвертации");
        System.out.println("conv_show - найти правило конвертации по уникальному номеру");
        System.out.println("conv_convert - преобразовать единицу в другую");
        System.out.println("conv_update - обновить информацию о правиле конвертации");
        System.out.println("conv_check_cycle - грубая проверка доступных правил конвертации на циклы");
        System.out.println("conv_delete - удалить правило конвертации");
        return true;
    }
}
