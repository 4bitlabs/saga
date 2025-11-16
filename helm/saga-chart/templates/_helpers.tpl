{{/*
Expande o nome do chart.
*/}}
{{- define "saga-chart.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Cria um nome de aplicação totalmente qualificado padrão.
Truncamos em 63 caracteres porque alguns campos de nome do Kubernetes são limitados a isso (pela especificação de nomenclatura DNS).
Se o nome do release contiver o nome do chart, ele será usado como nome completo.
*/}}
{{- define "saga-chart.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Cria o nome e versão do chart como usado pelo label do chart.
*/}}
{{- define "saga-chart.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Labels comuns
*/}}
{{- define "saga-chart.labels" -}}
helm.sh/chart: {{ include "saga-chart.chart" . }}
{{ include "saga-chart.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Labels de seletor
*/}}
{{- define "saga-chart.selectorLabels" -}}
app.kubernetes.io/name: {{ include "saga-chart.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
