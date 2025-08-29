## Goal 

The following are the goal(s) set out for this project.

1. Allow developers to query REST API with minimal yet essential data and get back a **view** of the following:
   - The network(s) the provider belongs to
   - The locations of where the provider practices
   - The organizations the provider is linked
2. Allow developers to query the endpoint free of charge.
   - If funding to cover server cost isn't available feel free to fork repository and deploy 👍 (You will need to register your app with the companies)
3. Provide comprehensive documentation to facilitate transparency & use of the REST API.

## Input Data

As of the writing of this document the input data required is as follows:

- npi (String) (NPI-1)
- primary_taxonomy (String)

The parameter names will be "npi" & "primary_taxonomy" respectively. The reason for primary taxonomy is to use it as a fallback.

## Primary Approach

The primary approach is to utilize the "PractitionerRole" endpoint as the central hub of where the application is getting the data. <br>
An important aspect of this project is to have one best case scenario for the query and then two fallback scenarios that ensure success. <br>
In the best case scenario the application will utilize the "PractitionerRole?practitioner.identifier=<npi-number>" endpoint. <br>
Thus, if the insurance company supports these parameters then the next step is to process the data for the practitioner. <br>
In contrast, if the insurance company does not support these parameter then the logic will route to the first fallback approach of obtaining the data. <br>

## First Fallback

In this approach the "Practitioner" endpoint will be queried with the "identifier" parameter, for example "Practitioner?identifier=<npi-number>". <br>
After this occurs the objective is to get the practitioner internal id and utilize it in the subsequent query against the "PractitionerRole". <br>
The query will utilize the "_id" parameter that is meant for internal ids, for example "PractitionerRole?_id=<internal-id>". <br>
Last of all, the data will then be processed as per the primary approach. In the event that the company does not support the identifier parameter then the application will route to the second fallback.

## Second Fallback

In this approach the "PractitionerRole" endpoint will be queried with the "specialty" parameter, for example "PractitionerRole?specialty=<primary-taxonomy>". <br>
After this occurs the objective is to iterate through all the entries and check if the npi matches the npi that was sent to the server. <br>
If this is true then the application will use a queue data structure to put the match entries on the queue and then later process the data (FIFO). <br>
