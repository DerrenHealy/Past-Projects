# Generated by Django 2.1 on 2018-09-04 10:17

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('vroom', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Car',
            fields=[
                ('car_id', models.AutoField(primary_key=True, serialize=False)),
                ('make_name', models.CharField(max_length=100)),
                ('model', models.CharField(max_length=100)),
                ('series', models.CharField(max_length=100)),
                ('seriesYear', models.IntegerField()),
                ('price_new', models.IntegerField()),
                ('engine_size', models.CharField(max_length=100)),
                ('fuel_system', models.CharField(max_length=100)),
                ('tank_capacity', models.CharField(max_length=100)),
                ('power', models.CharField(max_length=100)),
                ('seating_capacity', models.IntegerField()),
                ('standard_transmission', models.CharField(max_length=100)),
                ('body_type', models.CharField(max_length=100)),
                ('drive', models.CharField(max_length=100)),
                ('wheelbase', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Order',
            fields=[
                ('order_id', models.AutoField(primary_key=True, serialize=False)),
                ('create_date', models.DateField()),
                ('pickup_date', models.DateField()),
                ('return_date', models.DateField()),
                ('car_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='vroom.Car')),
            ],
        ),
        migrations.CreateModel(
            name='Role',
            fields=[
                ('role_id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='Store',
            fields=[
                ('store_id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=100)),
                ('address', models.CharField(max_length=100)),
                ('phone', models.CharField(max_length=20)),
                ('city', models.CharField(max_length=100)),
                ('state', models.CharField(max_length=100)),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('user_id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=100)),
                ('phone', models.CharField(max_length=20)),
                ('address', models.CharField(max_length=100)),
                ('birthday', models.DateField()),
                ('occupation', models.CharField(max_length=10)),
                ('gender', models.CharField(max_length=1)),
                ('password', models.CharField(max_length=100)),
                ('salt', models.CharField(max_length=100)),
                ('role_id', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='vroom.Role')),
            ],
        ),
        migrations.AddField(
            model_name='order',
            name='customer_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='vroom.User'),
        ),
        migrations.AddField(
            model_name='order',
            name='pickup_store',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='pickup_store', to='vroom.Store'),
        ),
        migrations.AddField(
            model_name='order',
            name='return_store',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='return_store', to='vroom.Store'),
        ),
    ]
