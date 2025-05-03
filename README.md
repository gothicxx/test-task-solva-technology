## Solva Technology.

## 💱 Test task. Junior Java Developer
Microservice consisting of two APIs, one client for accessing an external API, and using at least one database to store business entities and exchange rates.

It is required to develop a prototype of a microservice, without restricting access to the API, which will be integrated into the existing banking system.

The microservice should:
1. Receive information about each expenditure transaction in hryvnia (UAH) and other currencies in real time and save it in its own database (DB);
2. Store a monthly limit on expenditures in US dollars (USD) separately for two categories of expenses: goods and services. If not set, take the limit equal to 1000 USD;
3. Request data on exchange rates of currency pairs UAH / USD, and others, on a daily interval (1day / daily) and store them in its own database. When calculating rates, use closing data (close). If such data are not available for the current day (weekend or holiday), then use the data of the last close (previous_close);
4. Mark transactions that have exceeded the monthly transaction limit (technical flag limit_exceeded);
5. Allow the client to set a new limit. When setting a new limit, the microservice automatically sets the current date, not allowing it to be set in the past or future tense. Updating existing limits is prohibited;
6. Upon client request, return a list of transactions that have exceeded the limit, indicating the limit that was exceeded (setting date, limit amount, currency (USD)).