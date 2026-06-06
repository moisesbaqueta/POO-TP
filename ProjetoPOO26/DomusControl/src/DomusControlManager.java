
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class DomusControlManager implements Serializable {

    private DomusControl sistema;
    private Scanner scanner;
    private String utilizadorAtivo;

    public DomusControlManager() {
        this.sistema = new DomusControl();
        this.scanner = new Scanner(System.in);
        this.utilizadorAtivo = null;
    }

    public void run(String[] var0) throws Exception, IOException, ClassNotFoundException {
        int var3 = -1;
        System.out.println("\nBem vindo ao DomusControl\n");

        while (var3 != 0) {

            mostrarMenuPrincipal();
            System.out.println("Escolha uma opção: ");

            try {
                var3 = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException var5) {
                System.out.println("Opção inválida! Por favor, insira um número.");

                continue;
            }

            switch (var3) {
                case 0:
                    System.out.println("Obrigado por usar o DomusControl! Até à próxima!");
                    break;
                case 1:
                    menuUtilizadores();
                    break;
                case 2:
                    criarCasa();
                    break;
                case 3:
                    menuDivisao();
                    break;
                case 4:
                    menuDispositivo();
                    break;
                case 5:
                    System.out.println("\n Estado Atual do Sistema");
                    System.out.println(sistema.toString());
                    break;
                case 6:
                    System.out.println("Comandos a Dispositivos");
                    testarComando();
                    break;
                case 7:
                    menuEstatisticas();
                    break;
                case 8:
                    menuCenarios();
                    break;
                case 9:
                    avancarNoTempo();
                    break;
                case 10:
                    gravaFicheiro();
                    break;
                case 11:
                    carregaFicheiro();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    //funcoes auxiliares do meniu
    public static void mostrarMenuPrincipal() {
        System.out.println("\nMenu Principal\n");
        System.out.println("1. Menu de Utilizadores");
        System.out.println("2. Criar Casa");
        System.out.println("3. Menu de Divisões");
        System.out.println("4. Menu de Dispositivos");
        System.out.println("5. Mostrar Estado do Sistema");
        System.out.println("6. Enviar Comando a um Dispositivo");
        System.out.println("7. Estatísticas");
        System.out.println("8. Cenários");
        System.out.println("9. Avançar no Tempo");
        System.out.println("10. Gravar Dados em Ficheiro");
        System.out.println("11. Carregar Dados de Ficheiro");
        System.out.println("0. Sair");
    }

    public void menuUtilizadores() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n Menu de Utilizadores \n");
            System.out.println("Utilizador atual: " + (utilizadorAtivo != null ? utilizadorAtivo : "Nenhum"));
            System.out.println("1. Registar Novo Utilizador");
            System.out.println("2. Efetuar Login");
            System.out.println("3. Ver Lista de Utilizadores");
            System.out.println("4. Remover Utilizador");
            System.out.println("5. Definir Permissões");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    criarUtilizador();
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 3:
                    verListaUtilizadores();
                    break;
                case 4:
                    removerUtilizador();
                    break;
                case 5:
                    associarUtilizadorACasa();
                    break;
                case 0:
                    System.out.println("A voltar ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void menuDivisao() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n Menu de Divisões \n");
            System.out.println("1. Adicionar Nova Divisão");
            System.out.println("2. Remover Divisão Existente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Insira um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    adicionarDivisao();
                    break;
                case 2:
                    removerDivisao();
                    break;
                case 0:
                    System.out.println("A voltar ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void menuDispositivo() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n MENU DE DISPOSITIVOS \n");
            System.out.println("1. Adicionar Novo Dispositivo");
            System.out.println("2. Remover Dispositivo Existente");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Insira um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    adicionarDispositivo();
                    break;
                case 2:
                    removerDispositivo();
                    break;
                case 0:
                    System.out.println("A voltar ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void fazerLogin() {
        System.out.println("Insira o seu nome de utilizador: ");
        String nome = this.scanner.nextLine();

        if (sistema.getUtilizadores().containsKey(nome)) {
            this.utilizadorAtivo = nome;
            System.out.println("\nLogin efetuado! Bem-vindo, " + nome + ".\n");
        } else {
            System.out.println("Erro: Utilizador não encontrado!");
        }
    }

    public void criarUtilizador() {
        System.out.println("Insira o nome do utilizador: ");
        String nome = this.scanner.nextLine();

        Utilizador novoUtilizador = new Utilizador();
        novoUtilizador.setNome(nome);
        sistema.adicionarUtilizador(novoUtilizador);

        System.out.println("Utilizador criado! Seja bem-vindo, " + nome + "!");
    }

    public void verListaUtilizadores() {
        System.out.println("\n Lista de Utilizadores \n");
        if (sistema.getUtilizadores().isEmpty()) {
            System.out.println("Não existem utilizadores registados.");
        } else {
            for (Utilizador u : sistema.getUtilizadores().values()) {
                System.out.println("- " + u.getNome());
            }
        }
    }

    public void removerUtilizador() {
        System.out.println("Insira o nome do utilizador a remover: ");
        String nome = this.scanner.nextLine();

        if (sistema.removerUtilizador(nome)) {
            System.out.println("Utilizador removido com sucesso!");
        } else {
            System.out.println("Utilizador não encontrado.");
        }
    }

    public void associarUtilizadorACasa() {
        System.out.println("Insira o nome do utilizador: ");
        String nome = this.scanner.nextLine();

        if (!sistema.getUtilizadores().containsKey(nome)) {
            System.out.println("Utilizador não encontrado.");
            return;
        }

        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Casa não encontrada.");
            return;
        }

        System.out.println("Qual o nível de acesso nesta casa?");
        System.out.println("1 - Administrador");
        System.out.println("2 - Normal");
        String opcao = this.scanner.nextLine();

        TipoUtilizador permissao;
        if (opcao.equals("1")) {
            permissao = new Admin();
        } else if (opcao.equals("2")) {
            permissao = new Normal();
        } else {
            System.out.println("Opção inválida. Operação cancelada.");
            return;
        }
        Utilizador u = sistema.getUtilizadores().get(nome);
        u.adicionarCasa(morada, permissao);
        sistema.adicionarUtilizador(u);

        System.out.println("Sucesso! O utilizador " + nome + " é agora " + permissao.getDesignacao() + " na casa " + morada + ".");
    }

    public void criarCasa() {
        if (utilizadorAtivo == null) {
            System.out.println("Acesso Negado: Faça login para criar uma casa.");
            return;
        }

        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();
        System.out.println("Insira o nome do dono da casa: ");
        String dono = this.scanner.nextLine();

        Casa novaCasa = new Casa();
        novaCasa.setMorada(morada);
        novaCasa.setDono(dono);
        sistema.adicionarCasa(novaCasa);

        Utilizador u = sistema.getUtilizadores().get(utilizadorAtivo);
        u.adicionarCasa(morada, new Admin());
        sistema.adicionarUtilizador(u);

        System.out.println("Casa criada! Morada: " + morada + ", Dono: " + dono);
    }

    private boolean temPermissaoAdmin(String morada) {
        if (utilizadorAtivo == null) {
            return false;
        }

        Utilizador u = sistema.getUtilizadores().get(utilizadorAtivo);

        TipoUtilizador tipo = u.getCasasAcesso().get(morada);

        // Retorna true apenas se a permissão existir e for do tipo Admin
        return tipo instanceof Admin;
    }

    public void adicionarDivisao() {
        System.out.println("Em qual morada deseja adicionar a divisão? ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Erro: A casa com a morada '" + morada + "' não existe no sistema.");
            return;
        }

        if (!temPermissaoAdmin(morada)) {
            System.out.println("Acesso Negado: Apenas administradores podem alterar a estrutura desta casa.");
            return;
        }

        System.out.println("Qual o nome da divisão? ");
        String nomeDivisao = this.scanner.nextLine();

        Divisao novaDivisao = new Divisao(nomeDivisao, null);

        boolean sucesso = sistema.adicionarDivisaoCasa(morada, novaDivisao);
        if (sucesso) {
            System.out.println("Divisão " + nomeDivisao + " adicionada à casa em " + morada);
        } else {
            System.out.println("Já existe uma divisão chamada '" + nomeDivisao + "' nesta casa!");
        }
    }

    private void adicionarDispositivo() {
        System.out.println("\n Adicionar Dispositivo");
        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Erro: A casa com a morada '" + morada + "' não existe no sistema.");
            return;
        }

        if (!temPermissaoAdmin(morada)) {
            System.out.println("Acesso Negado: Apenas administradores podem alterar a estrutura desta casa.");
            return;
        }

        System.out.println("Insira o nome da divisao: ");
        String nomeDivisao = this.scanner.nextLine();

        System.out.println("Insira o tipo (Ex: Lampada, Cortina, ColunaSom, Rele, PortaoGaragem): ");
        String tipoClasse = this.scanner.nextLine().trim(); //esse trim tira os espaços extras no inicio/fim

        if (tipoClasse.equalsIgnoreCase("lampada")) {
            tipoClasse = "Lampada";
        } else if (tipoClasse.equalsIgnoreCase("cortina")) {
            tipoClasse = "Cortina";
        } else if (tipoClasse.equalsIgnoreCase("colunasom") || tipoClasse.equalsIgnoreCase("coluna som")) {
            tipoClasse = "ColunaSom";
        } else if (tipoClasse.equalsIgnoreCase("rele")) {
            tipoClasse = "Rele";
        } else if (tipoClasse.equalsIgnoreCase("portaogaragem") || tipoClasse.equalsIgnoreCase("portao garagem")) {
            tipoClasse = "PortaoGaragem";
        }

        System.out.println("Insira o ID do novo dispositivo: ");
        String id = this.scanner.nextLine();

        Casa casaAtual = sistema.getCasas().get(morada);
        if (casaAtual.existeDispositivo(id)) {
            System.out.println("Erro: Já existe um dispositivo com o ID '" + id + "' nesta casa!");
            return;
        }

        Dispositivo novoDispositivo = null;

        try {
            Class<?> classeDoDispositivo = Class.forName(tipoClasse);

            if (java.lang.reflect.Modifier.isAbstract(classeDoDispositivo.getModifiers())) {
                System.out.println("Erro: '" + tipoClasse + "' e uma classe abstrata. Escolha um tipo concreto (ex: Lampada).");
                return;
            }

            if (!Dispositivo.class.isAssignableFrom(classeDoDispositivo)) {
                System.out.println("Erro: '" + tipoClasse + "' nao e um tipo de dispositivo valido.");
                return;
            }

            novoDispositivo = (Dispositivo) classeDoDispositivo.getDeclaredConstructor().newInstance();
            novoDispositivo.setId(id);

            System.out.println("Insira o consumo base por hora em Wh: ");
            try {
                double consumo = Double.parseDouble(this.scanner.nextLine());
                novoDispositivo.setConsumoHora(consumo);
            } catch (NumberFormatException ex) {
                System.out.println("Valor inválido. A assumir consumo de 0.0 Wh.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Erro: O tipo '" + tipoClasse + "' nao foi encontrado.");
            return;
        } catch (Exception e) {
            System.out.println("Erro ao criar dispositivo: " + e.getMessage());
            return;
        }

        boolean sucesso = this.sistema.addDispDiv(morada, nomeDivisao, novoDispositivo);

        if (sucesso) {
            System.out.println("Dispositivo '" + id + "' adicionado com sucesso!");
        } else {
            System.out.println("Falha ao adicionar. A divisão não existe ou já existe um dispositivo com o mesmo ID.");
        }
    }

    public void removerDivisao() {
        System.out.println("\n Remover Divisão");
        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Erro: Casa não encontrada.");
            return;
        }

        if (!temPermissaoAdmin(morada)) {
            System.out.println("Acesso Negado: Apenas administradores podem remover divisões.");
            return;
        }

        System.out.println("Qual o nome da divisão a remover? ");
        String nomeDivisao = this.scanner.nextLine();

        boolean resultado = sistema.removerDivisaoCasa(morada, nomeDivisao);
        if (resultado) {
            System.out.println("Divisão '" + nomeDivisao + "' e todos os seus dispositivos foram removidos!");
        } else {
            System.out.println("Erro: A divisão '" + nomeDivisao + "' não existe nesta casa.");
        }
    }

    public void removerDispositivo() {
        System.out.println("\n Remover Dispositivo");
        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Erro: Casa não encontrada.");
            return;
        }

        if (!temPermissaoAdmin(morada)) {
            System.out.println("Acesso Negado: Apenas administradores podem remover dispositivos.");
            return;
        }

        System.out.println("Insira o nome da divisao: ");
        String nomeDivisao = this.scanner.nextLine();

        System.out.println("Insira o ID do dispositivo a remover: ");
        String id = this.scanner.nextLine();

        boolean sucesso = sistema.removerDispositivo(morada, nomeDivisao, id);
        if (sucesso) {
            System.out.println("Dispositivo '" + id + "' removido com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível remover. Verifique se a divisão e o ID estão corretos.");
        }
    }

    public void testarComando() {
        if (this.utilizadorAtivo == null) {
            System.out.println("Erro: Tem de fazer login primeiro no menu de utilizadores.");
            return;
        }

        System.out.println("\n Enviar Comando");
        System.out.println("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        if (!sistema.getCasas().containsKey(morada)) {
            System.out.println("Erro: A casa com a morada '" + morada + "' não existe no sistema.");
            return;
        }

        Utilizador u = sistema.getUtilizadores().get(this.utilizadorAtivo);
        if (u == null || !u.getCasasAcesso().containsKey(morada)) {
            System.out.println("Acesso Negado: Não tem permissão para operar dispositivos nesta casa.");
            return;
        }

        System.out.println("Insira o nome da divisão: ");
        String nomeDivisao = this.scanner.nextLine();

        System.out.println("Insira o ID do dispositivo: ");
        String idDispositivo = this.scanner.nextLine();

        System.out.println("Insira o comando: "); //(ex: LIGAR, DESLIGAR, INTENSIDADE, COR)
        String comando = this.scanner.nextLine().toUpperCase();

        double valor = 0.0;

        if (!comando.equalsIgnoreCase("LIGAR") && !comando.equalsIgnoreCase("DESLIGAR")) {
            System.out.print("Valor numérico: ");
            try {
                valor = Double.parseDouble(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido! Por favor, insira um número.");
                return;
            }
        }

        boolean resultado = sistema.enviarComando(morada, nomeDivisao, idDispositivo, comando, valor);
        if (resultado) {
            System.out.println("Comando executado com sucesso!");
        } else {
            System.out.println("Falha ao executar comando.");
        }
    }

    public void menuEstatisticas() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n MENU DE ESTATISTICAS \n");
            System.out.println("1. Casa que mais consome");
            System.out.println("2. Top 3 dispositivos mais usados numa casa");
            System.out.println("3. Top 3 divisões com mais dispositivos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    casaMaisConsumidora();
                    break;
                case 2:
                    top3Dispositivos();
                    break;
                case 3:
                    mostrarTop3Divisoes();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void casaMaisConsumidora() {
        Casa maisGasta = sistema.casaQueMaisConsome();

        if (maisGasta == null) {
            System.out.println("\nNão existem casas registadas ou não houve consumo ainda.");
        } else {
            System.out.println("\n CASA QUE MAIS CONSOME \n");
            System.out.println("Morada: " + maisGasta.getMorada());
            System.out.println("Dono: " + maisGasta.getDono());
            System.out.printf("Total Consumido: %.2f Wh\n", maisGasta.consumoTotalCasa());
        }
    }

    public void top3Dispositivos() {
        System.out.println("\n TOP 3 DISPOSITIVOS MAIS USADOS NUMA CASA ");
        System.out.print("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        List<Dispositivo> topTempo = sistema.top3TempoCasa(morada);
        List<Dispositivo> topAtivacoes = sistema.top3ActivacoesCasa(morada);

        if (topTempo == null) {
            System.out.println("Erro: Casa não encontrada no sistema.");
            return;
        }

        if (topTempo.isEmpty()) {
            System.out.println("Não existem dispositivos registados nesta casa.");
            return;
        }

        System.out.println("\n Ranking por Tempo de Uso");
        for (int i = 0; i < topTempo.size(); i++) {
            Dispositivo d = topTempo.get(i);
            System.out.printf("%dº Lugar: %s ID: %s | Tempo de uso: %.2f horas\n",
                    (i + 1), d.getClass().getSimpleName(), d.getId(), d.getTempoUso());
        }

        System.out.println("\n Ranking por Número de Ativações");
        for (int i = 0; i < topAtivacoes.size(); i++) {
            Dispositivo d = topAtivacoes.get(i);
            System.out.printf("%dº Lugar: %s ID: %s | Ativações: %d vezes\n",
                    (i + 1), d.getClass().getSimpleName(), d.getId(), d.getNumeroAtivacoes());
        }
    }

    public void mostrarTop3Divisoes() {
        System.out.println("\n TOP 3 DIVISÕES COM MAIS DISPOSITIVOS");

        List<String> res = sistema.top3Divisoes();

        if (res == null || res.isEmpty()) {
            System.out.println("Não existem divisões ou dispositivos registados no sistema.");
        } else {
            for (int i = 0; i < res.size(); i++) {
                System.out.println((i + 1) + "º Lugar -> " + res.get(i));
            }
        }
    }

    public void avancarNoTempo() {
        System.out.println("\n SIMULAÇÃO DE TEMPO");
        System.out.println("Quantas horas deseja avançar no sistema?");
        System.out.print("Horas: ");

        try {
            double horas = Double.parseDouble(this.scanner.nextLine());

            if (horas > 0) {
                sistema.avancarTempo(horas);
                System.out.println("\nO tempo avançou " + horas + " horas em todas as casas!");
                System.out.println("Os consumos foram atualizados com base nos dispositivos que estavam LIGADOS.");
            } else {
                System.out.println("\nO tempo deve ser um valor positivo!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n Valor inválido! Insira um número");
        }
    }

    public void menuCenarios() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n MENU DE CENARIOS \n");
            System.out.println("1. Sair de Casa");
            System.out.println("2. Jantar com Amigos");
            System.out.println("3. Deitar");
            System.out.println("4. Acordar");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(this.scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
                continue;
            }

            switch (opcao) {
                case 1:
                    aplicarSairDeCasa();
                    break;
                case 2:
                    aplicarJantarComAmigos();
                    break;
                case 3:
                    aplicarDeitar();
                    break;
                case 4:
                    aplicarAcordar();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void aplicarSairDeCasa() {
        System.out.println("\n CENÁRIO: SAIR DE CASA ");
        System.out.print("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        boolean sucesso = sistema.cenarioSairDeCasa(morada);

        if (sucesso) {
            System.out.println("Cenário 'Sair de Casa' aplicado.");
        } else {
            System.out.println("Casa não encontrada no sistema.");
        }
    }

    public void aplicarJantarComAmigos() {
        System.out.println("\n CENÁRIO: JANTAR COM AMIGOS ");
        System.out.print("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        boolean sucesso = sistema.cenarioJantarComAmigos(morada);

        if (sucesso) {
            System.out.println("Cenário 'Jantar com Amigos' aplicado.");
        } else {
            System.out.println("Casa não encontrada no sistema.");
        }
    }

    public void aplicarDeitar() {
        System.out.println("\n CENÁRIO: DEITAR ");
        System.out.print("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        boolean sucesso = sistema.cenarioDeitar(morada);

        if (sucesso) {
            System.out.println("Cenário 'Deitar' aplicado.");
        } else {
            System.out.println("Casa não encontrada no sistema.");
        }
    }

    public void aplicarAcordar() {
        System.out.println("\n CENÁRIO: ACORDAR ");
        System.out.print("Insira a morada da casa: ");
        String morada = this.scanner.nextLine();

        boolean sucesso = sistema.cenarioAcordar(morada);

        if (sucesso) {
            System.out.println("Cenário 'Acordar' aplicado.");
        } else {
            System.out.println("Casa não encontrada no sistema.");
        }
    }

    public void gravaFicheiro() throws FileNotFoundException, IOException {

        System.out.println("Insira o nome do ficheiro:");
        String fileName = scanner.nextLine();
        if (fileName.equals("exit")) {
            System.out.println("Operação cancelada...");
            return;
        }
        sistema.gravaBinario(fileName);
        System.out.println("Dados guardados com sucesso...");
    }

    public void carregaFicheiro() throws ClassNotFoundException, IOException {
        while (true) {
            System.out.println("Insira o nome do ficheiro a carregar:");
            try {
                String fileName2 = scanner.nextLine();
                if (fileName2.equals("exit")) {
                    System.out.println("Operação cancelada...");
                    return;
                }
                sistema = sistema.lerBinario(fileName2);
                System.out.println("Leitura feita com sucesso...");
                break;
            } catch (InvalidClassException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Ficheiro com formato inválido.");
            } catch (FileNotFoundException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Ficheiro não encontrado.");
            } catch (IOException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Erro na leitura do ficheiro.");
            } catch (ClassNotFoundException e) {
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Erro na leitura do ficheiro.");
            }
        }
    }
}
