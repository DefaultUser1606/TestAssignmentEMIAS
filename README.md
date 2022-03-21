# TestAssignmentEMIAS
## OrderService - занимается обработкой писем.
* POST http://localhost:8081/orders - принимает новое письмо

BODY:
{
    "FirstName":"TestFirstName",
    "Patronymic":"TestPatronymic",
    "LastName":"TestLastName",
    "GiftType":1
}

* POST http://localhost:8081/stockTransactions - создаёт новое движение на складе подарков

BODY:
{
  "Type":1,
  "Change":1,
  "ExternalId":1
}

## ChildBehaviourRatingService - сервис  заглушка, подбрасывает монетку при каждом обращении

* GET http://localhost:8082/childBehaviourRating/{childId} - оценивает поведение ребёнка(якобы)

## ToySupplier - "производит" игрушки для пополнения склада

POST: http://localhost:8083/ToySupplier/{toyType}?quantity= - "производит" новые игрушки в указанном количестве и оповещает OrderService о готовности
