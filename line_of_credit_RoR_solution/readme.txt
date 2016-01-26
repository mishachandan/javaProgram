-Login & Logout.

rails new locproject
rails generate scaffold User name:string password:digest
rake db:migrate
  rake db:create	
gem file >gem 'bcrypt', '~> 3.1.7'
bundle update
--
User_controller
rails g controller  Sessions new create destroy
rails g controller Admin index

================================================================

================================================================

rails generate scaffold User name:string password:digest

rails generate model Account  type:String principal:float User:references