class CreateAccounts < ActiveRecord::Migration
  def change
    create_table :accounts do |t|
      t.string :type
      t.float :principal
      t.references :User, index: true, foreign_key: true

      t.timestamps null: false
    end
  end
end
