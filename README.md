# Getting Started

### Lendico Challenge


**Instructions:**

* It's running in a Spring Boot Framework
* On 8080 port
* End-point http://localhost:8080/generate-plan


**payload:** 
```
  {  
      "loanAmount": "5000",  
      "nominalRate": "5.0",  
      "duration": 24,  
      "startDate": "2018-01-01T00:00:01Z" 
  } 
```
 

**response:** 
```
  {  
      [    
        {    
          "borrowerPaymentAmount": "219.36",    
          "date": "2018-01-01T00:00:00Z",    
          "initialOutstandingPrincipal": "5000.00",    
          "interest": "20.83",    
          "principal": "198.53",    
          "remainingOutstandingPrincipal": "4801.47",   
        },   
        {    
          "borrowerPaymentAmount": "219.36",    
          "date": "2018-02-01T00:00:00Z",    
          "initialOutstandingPrincipal": "4801.47",    
          "interest": "20.01",    "principal": "199.35",    
          "remainingOutstandingPrincipal": "4602.12",   
        },  
        
        ...   
        
        {    
          "borrowerPaymentAmount": "219.28",    
          "date": "2020-01-01T00:00:00Z",    
          "initialOutstandingPrincipal": "218.37",    
          "interest": "0.91",    
          "principal": "218.37",    
          "remainingOutstandingPrincipal": "0",   
         }  
       ] 
   }
```
