##RentalHive Project
Overview
#RentalHive is an advanced application designed for efficient management of equipment rental processes. After successfully completing the initial phase, focusing on conceptualization, planning, and implementing core features, the project is entering a crucial new stage.

#Project Objectives
In this phase, the goal is to extend the application's capabilities to include demand management, quotation generation, contract management, and file management. These additions aim to create a comprehensive solution, streamlining operations from inventory management to the complete equipment rental cycle.

#User Stories
Demand Management:
As a user, I can record a new equipment rental request with specific details such as equipment type, estimated rental duration, and desired start date.
As an inventory manager, I can view all pending requests and their statuses (pending, in progress, completed, canceled) for efficient priority management.
Quotation Generation:
As a rental manager, I can generate a detailed quote for an approved request, including selected equipment, estimated rental costs, and contractual conditions.
As a client, I can view and approve the quote to confirm equipment rental before signing the contract.
As an employee, I can track issued quotes, their status (pending, approved, rejected), and take necessary actions to convert them into contracts.
Contract Management:
As a rental manager, I can generate a detailed contract once the quote is approved, including terms and conditions, rental dates, final costs, and details of rented equipment.
As a user, I can view my active contracts, including details of rented equipment and rental start and end dates.
As a manager, I can archive completed contracts for future tracking and documentation.
File Management:
As a user, I can attach and store relevant files (signed contracts, invoices, equipment condition photos) to requests, quotes, and contracts for comprehensive documentation.
As a manager, I can easily access attached files for each request, quote, or contract for reference and auditing.
Project Structure
The project is organized with distinct controllers managing various aspects of the rental process:

ConditionController:

Manages conditions related to equipment rental.
ContratController:

Handles contract-related operations, including retrieval of active contracts, archiving contracts, and retrieving all contracts.
DevisController:

Manages the approval process for generated quotes.
DemandeController:

Controls the creation and retrieval of equipment rental requests.
EquipmentController:

Handles equipment-related operations, such as creation, retrieval, updating, and searching.
ArchieveController:

Manages the archiving process for various entities based on type and ID.
RentalRecordController:

Manages rental records, including retrieving rental history, recording new rentals, and deleting records.
Contributors
Abdelah
Boutaina 
Anas 
Abdelkrim 
A heartfelt thanks to all contributors for their valuable contributions to this learning journey.
