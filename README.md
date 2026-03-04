# **Предметная область 11: Справочник единиц и конвертация (Units & Conversion)**

## **Тип коллекции:**

LinkedHashMap(с сохранением порядка) 

## **Система хранит:** 
- **Unit** — единица (mg/L, g/L, mol/L) 
- **ConversionRule** — правило конвертации (из → в, коэффициент) 
- **ValueWithUnit** — значение + единица 
  
## Команды

| Команда | Описание | Пример | Проверяемые ошибки |
|---------|----------|--------|---------------------|
| `unit_add` | Добавить новую единицу | `unit_add` (интерактивно) | пустой код, слишком длинный код |
| `unit_list` | Показать все единицы | `unit_list` | — |
| `unit_show <id>` | Детальная информация о единице | `unit_show 1` | неверный ID |
| `conv_add` | Добавить правило конвертации | `conv_add` (интерактивно) | единица не найдена, коэффициент не число, коэффициент ≤ 0 |
| `conv_list` | Показать все правила | `conv_list` | — |
| `conv_convert <value> <fromCode> <toCode>` | Конвертировать значение | `conv_convert 1200 mg/L g/L` | отсутствие правила |
| `conv_delete <rule_id>` | Удалить правило | `conv_delete 10` | неверный ID |
| `unit_update <unit_id> field=value` | Обновить поле единицы | `unit_update 1 name="mg per liter"` | неизвестное поле, пустое имя |
| `conv_update <rule_id> factor=value` | Обновить коэффициент правила | `conv_update 10 factor=0.002` | коэффициент не число, ≤ 0 |
| `conv_check_cycle` | Грубая проверка циклов | `conv_check_cycle` | — |
 
## **Каркас классов:** 
```java
package ru.yourteam.lab.domain; 
 
import java.time.Instant; 
 
public final class Unit { 
    // Уникальный номер единицы. Программа назначает сама. 
    public long id; 
 
    // Короткий код единицы (например "mg/L"). Нельзя пустое. До 16 символов. 
    public String code; 
 
    // Человеческое название (например "milligrams per liter"). Нельзя пустое. До 64 символов. 
    public String name; 
 
    // Кто создал (логин). На ранних этапах можно "SYSTEM". 
    public String ownerUsername; 
 
    // Когда создано. Программа ставит автоматически. 
    public Instant createdAt; 
 
    // Когда обновляли. Программа обновляет автоматически. 6 
    public Instant updatedAt; 
} 
 
public final class ConversionRule { 
    // Уникальный номер правила. Программа назначает сама. 
    public long id; 
 
    // Из какой единицы (код, например "mg/L"). Нельзя пустое. 
    public String fromUnitCode; 
 
    // В какую единицу (код, например "g/L"). Нельзя пустое. 
    public String toUnitCode; 
 
    // Коэффициент: result = value * factor. Должен быть > 0. 
    public double factor; 
 
    // Кто создал правило (логин). На ранних этапах можно "SYSTEM". 
    public String ownerUsername; 
 
    // Когда создано. Программа ставит автоматически. 
    public Instant createdAt; 
 
    // Когда обновляли. Программа обновляет автоматически. 
    public Instant updatedAt; 
}

public final class ValueWithUnit { 
    // Числовое значение. 
    public double value; 
 
    // Код единицы (например "mg/L"). Нельзя пустое. 
    public String unitCode; 
}
```
