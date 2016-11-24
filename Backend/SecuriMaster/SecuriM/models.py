from django.db import models

class Guard(models.Model):
    
    id = models.IntegerField
    name = models.CharField
    
    class Meta:
        db_table = "guard"
        

class Post(models.Model):
    
    id = models.IntegerField
    name = models.CharField
    
    class Meta:
        db_table = "post"
        

class User(models.Model):
    
    id = models.IntegerField
    username = models.CharField
    password = models.CharField
    
    class Meta:
        db_table = "User"
        
        
class Points(models.Model):
    
    id = models.IntegerField
    nameOfRating = models.CharField
    critical = models.BooleanField
    
    class Meta:
        db_table = "points"
        
class CheckDetails(models.Model):
    
    id = models.IntegerField
    date = models.DateTimeField
    
    class Meta:
        db_table = "check_details"
