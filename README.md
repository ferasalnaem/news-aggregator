# API Documentation

The backend provides a RESTful API for retrieving news articles.

- Endpoint for fetching news via NewsApi:
Post <baseUrl>/api/aggregator/fetch

- Endpoint for retrieving categorized news
Get <baseUrl>/api/news


Parameters:

| Name     |  Type   |Description | 
| -------- | ------- | -------------------------------------- |
| category | String  | Filter articles by category            |
|  author  | String  | Filter articles by author              |
|  sortBy  | String  | Field to sort by (default: publishedAt)|
|  page    | String  | Page number for pagination             |
|  limit   | Integer | Number of articles per page            |
|  order   | String  | Sort order (asc or desc)               |



Sample Request:

curl -i 'http://<backend-url>/api/news?sortBy=publishedAt&page=0&limit=20'
Response Example:


    [
      {
        "id": "802a06bf0e8cf8c66833ad2afa356a9dbdfad954c2ddf6a5686bff208b7456b7",    
        "title": "Supreme Court refuses to delay Trump’s hush money sentencing - The Washington Post",
        "description": "President-elect Donald Trump turned to the Supreme Court in a last-ditch effort to stop the sentencing, citing the conservative majority’s explosive immunity opinion.",
        "content": "A closely divided Supreme Court refused to delay Donald Trumps sentencing in his hush money case, clearing the way for the president-elect to face judgment in a New York courtroom on Friday and to be… [+7493 chars]",
        "author": "Ann E. Marimow, Justin Jouvenal",
        "source": "The Washington Post",
        "url": "https://www.washingtonpost.com/politics/2025/01/09/trump-sentencing-supreme-court-new/",
        "urlToImage": "https://www.washingtonpost.com/wp-apps/imrs.php?src=https://arc-anglerfish-washpost-prod-washpost.s3.amazonaws.com/public/3OT5EO2ORY4NHV4FAJYWMPM3RU_size-normalized.jpg&w=1440",
        "publishedAt": "2025-01-10T01:47:22.000+00:00",
        "category": "POLITICS",
        "tags": null,
        "imageUrl": null
    },
    ...
    ]

