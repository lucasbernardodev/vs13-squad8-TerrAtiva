<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmação de Ação no Site TerrAtiva</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        h1 {
            color: #28a745;
            border-bottom: 2px solid #28a745;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        li {
            margin-bottom: 10px;
        }

        p {
            line-height: 1.5;
            margin-bottom: 20px;
        }

        footer {
            border-top: 2px solid #28a745;
            padding-top: 10px;
            color: #777;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Confirmação de ação no site TerrAtiva</h1>

        <p>Olá ${nome},</p>

        <p>Gostaríamos de informar que sua ação no site da TerrAtiva foi processada com sucesso. Abaixo estão os detalhes:</p>

        <ul>
            <li><strong>Ação realizada:</strong> ${acao}</li>
            <li><strong>Nome:</strong> ${nome}</li>
            <li><strong>ID:</strong> ${id}</li>
        </ul>

        <p>Fique à vontade para entrar em contato pelo E-mail ${email} caso tenha alguma dúvida ou precise de assistência adicional.</p>

        <footer>
            <p>Atenciosamente,<br>
            Equipe TerrAtiva</p>
        </footer>
    </div>
</body>
</html>
